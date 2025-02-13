# Recipe Scraper - https://www.seriouseats.com/

import requests
from bs4 import BeautifulSoup as bs
import json
import re
from typing import List
from utilities import Utilities


def get_links_from_serious_eats():
    links = set()
    titles = set()
    url = 'https://www.seriouseats.com/nigerian-recipes-5117264'
    r = requests.get(url)
    root_html = bs(r.text, "html.parser")

    # Get Header
    header_recipes = root_html.find("div", {"class": "mntl-document-spotlight"}).find_all('a')
    for header_blogs in header_recipes:
        links.add(header_blogs['href'])
        titles.add(header_blogs.find("span", {"class": "card__title-text"}).text)

    # Get Body
    listed_recipes = root_html.find("div", {"id": "mntl-taxonomysc-article-list_1-0"}).find_all('a')
    for listed_blogs in listed_recipes:
        blog_title = listed_blogs.find("span", {"class": "card__title-text"}).text

        # Skip intro to Nigerian Foods article
        if blog_title == 'Nigerian Food 101: Recipes to Get You Started':
            continue
        links.add(listed_blogs['href'])
        titles.add(blog_title)

    # print(f"Links: {links} \n- {len(links)}")
    # print(f"Titles: {titles} \n- {len(titles)}")
    return links, titles


def scrape_recipe_from_links(urls: List[str]):
    json_objs = []

    # Scrape each link
    for url in urls:
        json_objs.append(scrape_recipe_from_link(url))
    # print(len(json_objs))

    # Grab ingredients from scraped recipes
    return json_objs, extract_unique_ingredients(json_objs)


def scrape_recipe_from_link(url):
    # print(f"Currently Scraping: {url}")
    # Create JSON object & return it
    recipe_dict = {
        "recipe_name": None,  # String
        "author": None,  # String
        "description": None,  # String
        "servings": None,  # Integer
        "prep_time": None,  # String
        "cook_time": None,  # String
        "marinating_time": None,  # Optional String
        "total_time": None,  # String
        "url": None,  # String
        "categories": [],  # Empty list of Strings for categories  <---->
        "ingredients": [],  # List of ingredient dictionaries, empty for now
        "steps": [],  # List of steps dictionaries, empty for now     <---->
        "image": None,  # String
        "special_equipment": None,  # Optional String
        "nutrition": None
    }

    r = requests.get(url)
    soup = bs(r.text, "html.parser")
    recipe_details = soup.find("div", {"class": "project-meta__times-container"})

    special = soup.find("div", {"id": "structured-project__steps_1-0"}).find("span", {
        "id": "toc-special-equipment"})
    prep_time = recipe_details.find("div", {"class": "prep-time"})
    cook_time = recipe_details.find("div", {"class": "cook-time"})
    marinating_time = recipe_details.find("div", {"class": "custom-time project-meta__custom-time"})
    total_time = recipe_details.find("div", {"class": "total-time"})

    recipe_dict['recipe_name'] = soup.find("h1", {"class": "heading__title"}).text
    recipe_dict['author'] = soup.find("a", {"class": "mntl-attribution__item-name"}).text
    recipe_dict['image'] = soup.find("figure", {"id": "primary-image_1-0"}).find("img")["src"]
    recipe_dict['description'] = soup.find("p", {"class": "heading__subtitle"}).text
    recipe_dict['categories'].append(
        soup.find_all("li", {"class": "comp mntl-breadcrumbs__item mntl-block"})[-1].text.replace('\n', '').strip())
    recipe_dict['servings'] = int(re.findall(r'\d+',
                                             soup.find("div", {"class": "project-meta__recipe-serving"}).find("span", {
                                                 "class": "meta-text__data"}).text)[0])

    if special:
        recipe_dict['special_equipment'] = special.findNext().findNext().findNext().text.strip()

    if prep_time:
        recipe_dict['prep_time'] = prep_time.find(
            "span", {
                "class": "meta-text__data"}).text

    if cook_time:
        recipe_dict['cook_time'] = cook_time.find(
            "span", {
                "class": "meta-text__data"}).text

    if marinating_time:
        recipe_dict['marinating_time'] = marinating_time.find(
            "span", {
                "class": "meta-text__data"}).text

    if total_time:
        recipe_dict['total_time'] = total_time.find(
            "span", {
                "class": "meta-text__data"}).text
    else:
        recipe_dict['total_time'] = recipe_details.find("div", {"class": "active-time"}).find(
            "span", {
                "class": "meta-text__data"}).text

    recipe_dict['url'] = url
    recipe_dict['ingredients'] = []

    # Parse Ingredients

    # soup.find_all("ul", {"class": "structured-ingredients__list"})[0].find_all("p")[-1].text  <- Based off that
    ingredient_headings = soup.find_all("ul", {"class": "structured-ingredients__list"})

    if ingredient_headings:
        for ingredients_heading in ingredient_headings:
            for ingredient in ingredients_heading.find_all("p"):
                ingredient_text = ingredient.text.strip()
                if '\xa0' in ingredient_text:
                    ingredient_text = ingredient_text.replace(u'\xa0', u' ')

                recipe_dict['ingredients'].append(parse_ingredient(ingredient_text))
    else:
        ingredient_list = soup.find_all("li", {"class": "simple-list__item"})

        for ingredient in ingredient_list:
            if ingredient.text.strip()[-1] == ':':
                continue

            ingredient_text = ingredient.text.strip()
            if '\xa0' in ingredient_text:
                ingredient_text = ingredient_text.replace(u'\xa0', u' ')

            recipe_dict['ingredients'].append(parse_ingredient(ingredient_text))

    # print(f"{recipe_dict['ingredients']}")

    # Parse Nutrition
    nutrition_table = soup.find_all("tr", {"class": "nutrition-info__table--row"})
    calories = None
    fat = None
    carbs = None
    protein = None

    for nutrition in nutrition_table:
        nutrition = nutrition.text.strip().replace('\n', ' ')

        if 'Calories' in nutrition:
            calories = nutrition
        elif 'Fat' in nutrition:
            fat = nutrition
        elif 'Carbs' in nutrition:
            carbs = nutrition
        elif 'Protein' in nutrition:
            protein = nutrition

    recipe_dict['nutrition'] = build_nutrition_obj(calories, fat, carbs, protein)

    # Parse Steps
    steps = soup.find_all("li", {"class": "comp mntl-sc-block mntl-sc-block-startgroup mntl-sc-block-group--LI"})

    for index, step in enumerate(steps):
        step = step.text.replace('\n', ' ').strip()
        recipe_dict['steps'].append(build_step_obj(index, step))

    # print(recipe_dict)
    return recipe_dict


def parse_ingredient(ingredient_str: str):
    return {"name": ingredient_str.strip(),
            "category": categorize_ingredient(ingredient_str) if ingredient_str else None}


def categorize_ingredient(name) -> str:
    name = name.lower()
    categories = Utilities.get_categories()

    # Check for some common spice/herb descriptors
    if any(descriptor in name for descriptor in
           ['fresh', 'dried', 'powdered', 'ground', 'leaves', 'alligator', 'extract']):
        return 'herb_spice'

    for category, keywords in categories.items():
        if any(keyword in name for keyword in keywords):
            return category

    return 'other'


def build_nutrition_obj(calories: str, fat: str, carbs: str, protein: str):
    return {  # Nutrition dictionary
        "calories": calories,  # String
        "fat": fat,  # String
        "carbs": carbs,  # String
        "protein": protein  # String
    }


def build_step_obj(index: int, step: str):
    return {
        "step_number": index+1,
        "instruction": step,
    }


def extract_unique_ingredients(recipes):
    seen_ingredients = set()
    ingredient_list = []

    # Get names for all ingredients
    for recipe in recipes:
        for recipe_ingredient in recipe['ingredients']:
            name = simplify_ingredient_name(recipe_ingredient['name'])

            # Check if we have seen it before
            if name not in seen_ingredients:
                seen_ingredients.add(name)
                ingredient_list.append({
                    "name": name,
                    "actual": recipe_ingredient['name'],
                    "recipe_name": recipe['recipe_name'],
                    "url": recipe['url'],
                    "category": recipe_ingredient["category"]
                })
    return ingredient_list


def simplify_ingredient_name(recipe_ingredient: str):
    categories = Utilities.get_categories()

    for category, ingredients in categories.items():
        for ingredient in ingredients:
            if ingredient.lower() in recipe_ingredient.lower():
                return ingredient
    return "Name Error"


links, titles = get_links_from_serious_eats()
reg, uniq = scrape_recipe_from_links(links)
scrape_recipe_from_link("https://www.seriouseats.com/nigerian-beef-stew-recipe-6889635")
print(f'{json.dumps(reg, indent=4)}')
