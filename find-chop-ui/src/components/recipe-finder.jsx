"use client"

import { useState } from "react"
import Link from "next/link"
import { Textarea } from "@/components/ui/textarea"
import { Button } from "@/components/ui/button"
import { DropdownMenu, DropdownMenuTrigger, DropdownMenuContent, DropdownMenuItem } from "@/components/ui/dropdown-menu"
import { Label } from "@/components/ui/label"
import { Checkbox } from "@/components/ui/checkbox"
import { Card, CardHeader, CardContent } from "@/components/ui/card"
import Header from "./header"
import Footer from "./footer"

export default function Component() {
  const [ingredients, setIngredients] = useState("")
  const [selectedIngredients, setSelectedIngredients] = useState([])
  const [activeCategory, setActiveCategory] = useState("all")
  const [showCategoryDropdown, setShowCategoryDropdown] = useState(false)
  const [showRecipeResults, setShowRecipeResults] = useState(false)
  const [showMissingIngredientsPrompt, setShowMissingIngredientsPrompt] = useState(false)
  const [recipes, setRecipes] = useState([])
  const [topRecipes, setTopRecipes] = useState([])
  const [isLoading, setIsLoading] = useState(false)
  const handleIngredientChange = (e) => {
    const newIngredients = e.target.value.split(",").map((item) => item.trim())
    setSelectedIngredients(newIngredients)
    setIngredients(newIngredients.join(", "))
  }

  const handleIngredientSelect = (ingredient) => {

    /*
    if (selectedIngredients.includes(ingredient)) {
      setSelectedIngredients(selectedIngredients.filter((item) => item !== ingredient))
      setIngredients(selectedIngredients.filter((item) => item !== ingredient).join(", "))
    } else {
      setSelectedIngredients([...selectedIngredients, ingredient])
      setIngredients([...selectedIngredients, ingredient].join(", "))
    }
    */

    // handle for all ingredients option
    if (ingredient === "all") {
      if (selectedIngredients.length === ingredientOptions.length) {
        setSelectedIngredients([]);
        setIngredients("")
      } else {
        setSelectedIngredients(ingredientOptions.map((option) => option.name))
        setIngredients(ingredientOptions.map((option) => option.name).join(","));
      }
    } else {
      if (selectedIngredients.includes(ingredient)) {
        setSelectedIngredients(selectedIngredients.filter((item) => item !== ingredient));
        setIngredients(selectedIngredients.filter((item) => item !== ingredient).join(", "))
      } else {
        setSelectedIngredients([...selectedIngredients, ingredient])
        setIngredients([...selectedIngredients, ingredient].join(", "))
      }
    }
  }

  const handleCategoryClick = (category) => {
    setActiveCategory(category)
    setShowCategoryDropdown(false)
  }

  const handleCategoryDropdownToggle = (e) => {
    e.preventDefault()
    setShowCategoryDropdown(!showCategoryDropdown)
  }

  const handleFindRecipes = async () => {
    setIsLoading(true)
    try {
      const response = await fetch("http://localhost:8099/api/find-chop", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ ingredients: selectedIngredients }),
      })
      const data = await response.json();
      console.log(data);
      setRecipes(data.recipes);
      setShowRecipeResults(true);
      setShowMissingIngredientsPrompt(true);
    } catch (error) {
      console.error("Error fetching recipes:", error);
    } finally {
      setIsLoading(false)
    }
  }

  const handleFindTopRecipes = async () => {
    setIsLoading(true)
    try {
      const response = await fetch("http://localhost:8097/api/v1/click-counter/top-recipes", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ ingredients: selectedIngredients }),
      }) 
      // Set Recommended Recipes Components
      const data = await response.json();
      console.log(data);
      setTopRecipes(data.recipes)
    } catch (error) {
      console.error("Error fetching recipes:", error)
    } finally {
      setIsLoading(false)
    }
  }

  const clickCounter = async (recipe, event) => {
    try {
      const response = await fetch("http://localhost:8097/api/v1/click-counter", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          recipe_name: recipe,
        }),
      });
  
      if (!response.ok) {
        throw new Error("Failed to update click count");
      }
  
      console.log("Click count updated successfully for ", recipe);
    } catch (error) {
      console.error("Error:", error);
    }
  };

  const ingredientCategories = [
    { name: "all", label: "All" },
    { name: "meat", label: "Meat" },
    { name: "veggies", label: "Veggies" },
    { name: "dairy", label: "Dairy" },
    { name: "flavoring", label: "Flavoring" },
    { name: "fruit", label: "Fruit" },
    { name: "grain", label: "Grain" },
    { name: "herb_spice", label: "Herb & Spice" },
    { name: "legume", label: "Legume" },
    { name: "leavening_agent", label: "Leavening Agent" },
    { name: "liquid", label: "Liquid" },
    { name: "nut", label: "Nut" },
    { name: "oil", label: "Oil" },
    { name: "other", label: "Other" },
    { name: "starch", label: "Starch" },
    { name: "sweetener", label: "Sweetener" },
    { name: "thickener", label: "Thickener" },
  ]
  const ingredientOptions = [
    {
      name: "all",
      category: "all",
    },
    {
      name: "beef",
      category: "meat",
    },
    {
      name: "cloves",
      category: "herb_spice",
    },
    {
      name: "grains of selim",
      category: "herb_spice",
    },
    {
      name: "crayfish",
      category: "meat",
    },
    {
      name: "crayfish powder",
      category: "herb_spice",
    },
    {
      name: "salt",
      category: "herb_spice",
    },
    {
      name: "dry pepper",
      category: "herb_spice",
    },
    {
      name: "amaranth",
      category: "veggies",
    },
    {
      name: "spinach",
      category: "veggies",
    },
    {
      name: "tomato",
      category: "veggies",
    },
    {
      name: "nutmeg",
      category: "herb_spice",
    },
    {
      name: "sugar",
      category: "sweetener",
    },
    {
      name: "palm oil",
      category: "oil",
    },
    {
      name: "iru",
      category: "herb_spice",
    },
    {
      name: "scotch bonnet pepper",
      category: "veggies",
    },
    {
      name: "lemongrass",
      category: "veggies",
    },
    {
      name: "yam",
      category: "veggies",
    },
    {
      name: "cornstarch",
      category: "starch",
    },
    {
      name: "water",
      category: "liquid",
    },
    {
      name: "vegetable oil",
      category: "oil",
    },
    {
      name: "ground beef",
      category: "meat",
    },
    {
      name: "onion",
      category: "veggies",
    },
    {
      name: "potato",
      category: "veggies",
    },
    {
      name: "bok choy",
      category: "veggies",
    },
    {
      name: "carrot",
      category: "veggies",
    },
    {
      name: "curry powder",
      category: "herb_spice",
    },
    {
      name: "thyme",
      category: "herb_spice",
    },
    {
      name: "nigerian-style stock",
      category: "meat",
    },
    {
      name: "bell pepper",
      category: "veggies",
    },
    {
      name: "black pepper",
      category: "herb_spice",
    },
    {
      name: "flour",
      category: "grain",
    },
    {
      name: "baking powder",
      category: "leavening_agent",
    },
    {
      name: "butter",
      category: "dairy",
    },
    {
      name: "egg",
      category: "meat",
    },
    {
      name: "peas",
      category: "legume",
    },
    {
      name: "urheri",
      category: "herb_spice",
    },
    {
      name: "erhe",
      category: "herb_spice",
    },
    {
      name: "gbafilo",
      category: "herb_spice",
    },
    {
      name: "umilo",
      category: "herb_spice",
    },
    {
      name: "uziza",
      category: "herb_spice",
    },
    {
      name: "chicken stock",
      category: "meat",
    },
    {
      name: "ginger",
      category: "veggies",
    },
    {
      name: "garlic",
      category: "veggies",
    },
    {
      name: "chicken",
      category: "meat",
    },
    {
      name: "habanero pepper",
      category: "veggies",
    },
    {
      name: "bay leaves",
      category: "herb_spice",
    },
    {
      name: "groundnut",
      category: "nut",
    },
    {
      name: "paprika",
      category: "herb_spice",
    },
    {
      name: "plantain",
      category: "veggies",
    },
    {
      name: "onion powder",
      category: "herb_spice",
    },
    {
      name: "garlic powder",
      category: "herb_spice",
    },
    {
      name: "cayenne pepper",
      category: "veggies",
    },
    {
      name: "peanut oil",
      category: "oil",
    },
    {
      name: "lime",
      category: "fruit",
    },
    {
      name: "coriander leaves",
      category: "herb_spice",
    },
    {
      name: "peanuts",
      category: "nut",
    },
    {
      name: "moin moin leaves",
      category: "veggies",
    },
    {
      name: "coconut oil",
      category: "oil",
    },
    {
      name: "scallion",
      category: "veggies",
    },
    {
      name: "rice",
      category: "grain",
    },
    {
      name: "sweet corn",
      category: "veggies",
    },
    {
      name: "coconut milk",
      category: "liquid",
    },
    {
      name: "tomato paste",
      category: "veggies",
    },
    {
      name: "basil leaves",
      category: "herb_spice",
    },
    {
      name: "bread",
      category: "grain",
    },
    {
      name: "bitter leaf",
      category: "veggies",
    },
  ]
  const filteredIngredients = ingredientOptions.filter(
    (option) => activeCategory === "all" || option.category === activeCategory,
  )

  const missingIngredients = ingredientOptions.filter((option) => !selectedIngredients.includes(option.name))
  return (
    <div className="flex flex-col min-h-screen bg-background text-foreground">
      <Header/>
      <main className="flex-1 py-12 px-6">
        <div className="container mx-auto max-w-3xl">
          <center>
            <h1 className="text-3xl font-bold mb-8">Find Chop Fast! Cook Naija Meals with What You Get!</h1>
            <p>If you no get time to choose ingredients, check all our recipes for here 🤌🏾!</p>
            <Link href="/recipes" style={{ color: 'blue', fontWeight: 'bold' }}>
            here
            </Link>
            <br />
        </center>
          <form className="bg-muted rounded-lg p-6 mb-12">
            <div className="grid gap-4">
              <div>
                <label htmlFor="ingredients" className="block text-sm font-medium mb-1">
                  Enter the ingredients you have:
                </label>
                <Textarea
                  id="ingredients"
                  value={ingredients}
                  onChange={handleIngredientChange}
                  placeholder="Eggs, chicken, broccoli, etc."
                  className="w-full rounded-md border-input bg-background text-foreground focus:border-primary focus:ring-primary"
                  rows={3}
                />
              </div>
              <div>
                <label className="block text-sm font-medium mb-1">Or select from the following ingredients:</label>
                <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 gap-2 overflow-x-auto">
                  {ingredientCategories
                    .filter((category) => ["all", "meat", "veggies"].includes(category.name))
                    .map((category) => (
                      <Button
                        key={category.name}
                        variant={activeCategory === category.name ? "primary" : "outline"}
                        onClick={(e) => {
                          e.preventDefault()
                          handleCategoryClick(category.name)
                        }}
                        className={`w-full ${activeCategory === category.name ? "border-2 border-primary" : ""}`}
                      >
                        {category.label}
                      </Button>
                    ))}
                  <DropdownMenu>
                    <DropdownMenuTrigger asChild>
                      <Button
                        variant={showCategoryDropdown ? "primary" : "outline"}
                        className={`w-full ${showCategoryDropdown ? "border-2 border-primary" : ""}`}
                        onClick={handleCategoryDropdownToggle}
                      >
                        More
                        <ChevronDownIcon className="h-4 w-4 ml-2" />
                      </Button>
                    </DropdownMenuTrigger>
                    {showCategoryDropdown && (
                      <DropdownMenuContent className="w-48 max-h-[300px] overflow-auto" align="start">
                        {ingredientCategories
                          .filter((category) => !["all", "meat", "veggies"].includes(category.name))
                          .map((category) => (
                            <DropdownMenuItem
                              key={category.name}
                              onClick={(e) => {
                                e.preventDefault()
                                handleCategoryClick(category.name)
                              }}
                              className={`${
                                activeCategory === category.name ? "bg-primary text-primary-foreground" : ""
                              }`}
                            >
                              {category.label}
                            </DropdownMenuItem>
                          ))}
                      </DropdownMenuContent>
                    )}
                  </DropdownMenu>
                </div>
                <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 gap-2 mt-4 max-h-[200px] overflow-y-auto">
                  {filteredIngredients.map((option) => (
                    <Label key={option.name} className="flex items-center gap-2 font-normal cursor-pointer">
                      <Checkbox
                        checked={selectedIngredients.includes(option.name)}
                        onCheckedChange={() => handleIngredientSelect(option.name)}
                      />
                      {option.name}
                    </Label>
                  ))}
                </div>
              </div>
              <Button type="button" onClick={handleFindRecipes} className="w-full" disabled={isLoading}>
                {isLoading ? "Loading..." : "Find Recipes"}
              </Button>
            </div>
          </form>

          {/* Ingredients Results */}
          {showRecipeResults && (
            <div>
              <h2 className="text-2xl font-bold mb-4">You can make {recipes.length} recipe{recipes.length > 1 ? "s" : ""}!</h2>
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
                {recipes.map((recipe) => (
                  <Card key={recipe.id}>
                    <CardHeader>
                      <img
                        src={recipe.image}
                        alt={recipe.recipe_name}
                        width={400}
                        height={225}
                        className="rounded-t-lg object-cover w-full"
                        style={{ aspectRatio: "400/225", objectFit: "cover" }}
                      />
                    </CardHeader>
                    <CardContent className="p-4 flex flex-col items-center">
                      <h3 className="text-lg font-bold mb-2">{recipe.recipe_name}</h3>
                      <p className="text-sm text-muted-foreground mb-4">{recipe.description}</p>
                      <Link href={recipe.url} className="text-primary hover:underline" prefetch={false}>
                        <button 
                        type="button" 
                        className="bg-primary text-white dark:text-gray-900 py-2 px-4 rounded hover:bg-primary-dark"
                        onMouseDown={() => clickCounter(recipe.recipe_name, event)}>
                            View Recipe
                        </button>
                      </Link>
                    </CardContent>
                  </Card>
                ))}
              </div>
            </div>
          )}
          
          {/* Missing Ingredients */}
          {showMissingIngredientsPrompt && (
            <div className="mt-8">
              <h2 className="text-2xl font-bold mb-4">Add missing ingredients to unlock more recipes!</h2>
              <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 gap-2">
                {missingIngredients.map((option) => (
                  <Button
                    key={option.name}
                    variant="outline"
                    onClick={() => handleIngredientSelect(option.name)}
                    className="w-full"
                  >
                    {option.name}
                  </Button>
                ))}
              </div>
            </div>
          )}

          {/* Recommended Recipes */}
          {/* Recommended Recipes -> Replace the cards with the card for loop from showResults */}
          <div className="mt-12">
            <h2 className="text-2xl font-bold mb-4">Recommended Recipes</h2>
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
              {topRecipes.map((topRecipes) => (
                <Card key={recipe.id}>
                  <CardHeader>
                    <img 
                      src={topRecipes.image}
                      alt={topRecipes.recipe_name}
                      width={400}
                      height={225}
                      className="rounded-t-lg object-cover w-full"
                      style={{ aspectRatio: "400/225", objectFit: "cover" }}
                    />
                  </CardHeader>
                  <CardContent className="p-4 flex flex-col items-center">
                  <h3 className="text-lg font-bold mb-2">{topRecipes.recipe_name}</h3>
                      <p className="text-sm text-muted-foreground mb-4">{topRecipes.description}</p>
                      <Link href={topRecipes.url} className="text-primary hover:underline" prefetch={false}>
                        <button 
                        type="button" 
                        className="bg-primary text-white dark:text-gray-900 py-2 px-4 rounded hover:bg-primary-dark"
                        onMouseDown={() => clickCounter(topRecipes.recipe_name, event)}>
                            View Recipe
                        </button>
                      </Link>
                  </CardContent>
                </Card>
              ))}
            </div>
          </div>
        </div>
      </main>
      <Footer/>
    </div>
  )
}

function ChevronDownIcon(props) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <path d="m6 9 6 6 6-6" />
    </svg>
  )
}
