
export default function FufuIcon(props) {
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
        {/* Plate - slightly elliptical to show perspective */}
        <ellipse cx="12" cy="14" rx="10" ry="8" />
        
        {/* Fufu - represented as a circular mound */}
        <circle cx="12" cy="12" r="6" />
        
        {/* Stick/pestle - angled line */}
        <line x1="14" y1="8" x2="16" y2="4" />
      </svg>
    )
  }
  