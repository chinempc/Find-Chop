
export default function NigerianFlagIcon(props) {
    return (
        <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="32"
      height="32"
      viewBox="0 0 24 24"
      className="h-8 w-10 ml-1"
    >
      {/* Base */}
      <rect x="9" y="20" width="6" height="2" fill="#808080"/>
      
      {/* Pole */}
      <rect x="11.5" y="6" width="1" height="14" fill="#808080"/>
      
      {/* Pole top */}
      <rect x="11" y="5" width="2" height="1" rx="0.5" fill="#808080"/>
      
      {/* Flag - made each stripe wider */}
      <rect x="12.5" y="7" width="4" height="10" fill="#008751"/> {/* Left green */}
      <rect x="16.5" y="7" width="4" height="10" fill="#FFFFFF"/> {/* Middle white */}
      <rect x="20.5" y="7" width="4" height="10" fill="#008751"/> {/* Right green */}
    </svg>
    );
  }
  