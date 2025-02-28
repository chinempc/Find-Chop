import Link from "next/link"
import FufuIcon from "./icons/fufuicon";
import NigerianFlagIcon from "./icons/nigerianFlagIcon";
import ModeToggle from "./ModeToggle";

export default function Header() {
    return (
        <header className="sticky top-0 z-10 py-4 px-6 border-b bg-background">
        <div className="container mx-auto flex justify-between items-center">
          <Link href="/" className="flex items-center text-2xl font-bold" prefetch={false}>
            <FufuIcon className="h-6 w-6 mr-2" />
            Find Chop 
            <NigerianFlagIcon/>
          </Link>
          <nav className="flex items-center gap-4 overflow-x-auto whitespace-nowrap">
            <Link href="/about" className="text-sm hover:underline" prefetch={false}>
              About
            </Link>
            <Link href="#" className="text-sm hover:underline" prefetch={false}>
              Contact
            </Link>
            <ModeToggle/>
          </nav>
        </div>
      </header>
    );
}
