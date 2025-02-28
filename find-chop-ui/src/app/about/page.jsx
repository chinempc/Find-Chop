"use client"

import Header from "@/components/header"
import Footer from "@/components/footer"

export default function About() {
  return (
    <div className="flex flex-col min-h-screen bg-background text-foreground">
      <Header/>
      <main className="flex-1 py-12 px-6">
        <div className="container mx-auto max-w-3xl">
          <div className="grid md:grid-cols-2 gap-8">
            <div className="bg-muted rounded-lg p-6 flex flex-col justify-center">
              <h1 className="text-3xl font-bold mb-4">About Recipe Finder</h1>
              <p className="text-muted-foreground mb-6">
                Recipe Finder is your go-to Nigerian recipe finder, making it easy to cook delicious meals with what you
                have. Whether you're craving classic dishes like Jollof Rice or experimenting with local ingredients, we
                help you find authentic Nigerian recipes that fit your pantry. Our platform is designed to celebrate the
                rich culinary heritage of Nigeria, offering recipes from across the country. No matter your cooking
                skills, Find Chop has something for everyone — explore, cook, and enjoy!
              </p>
            </div>
            <div className="bg-muted rounded-lg p-6">
              <h2 className="text-2xl font-bold mb-4">Our Story</h2>
              <p className="text-muted-foreground mb-6">
                Founded in 2023, Find Chop began as a personal project, born out of the frustration of not finding
                Nigerian recipes on traditional recipe apps. As someone passionate about my culture’s food, I wanted an
                easier way to discover and cook the dishes I grew up with. What started as a simple solution for myself
                has grown into a platform where anyone can explore authentic Nigerian recipes. Find Chop is all about
                sharing the rich, diverse flavors of Nigeria with the world.
              </p>
              <h2 className="text-2xl font-bold mb-4">Our Team</h2>
              <p className="text-muted-foreground mb-6">
                The recipes featured on Find Chop are sourced from trusted platforms like{" "}
                <a href="#" className="text-primary hover:underline">
                  Serious Eats
                </a>
                , ensuring that the dishes we showcase are authentic and well-crafted. You can explore our code and
                contribute to the project on our{" "}
                <a href="#" className="text-primary hover:underline">
                  GitHub page
                </a>
                , where we continually work to make Nigerian recipes more accessible to everyone.
              </p>
            </div>
          </div>
          <div className="bg-muted rounded-lg p-6 mt-8">
            <h2 className="text-2xl font-bold mb-4">Get in Touch</h2>
            <p className="text-muted-foreground mb-6">
              If you have any questions, feedback, or suggestions, we'd love to hear from you. You can reach us at{" "} 
              <a href="#" className="text-primary hover:underline">
                info@recipesfinder.com 
              </a>
              , or by using the contact form on our website.
            </p>
          </div>
        </div>
      </main>
      <Footer/>
    </div>
  )
}
