/** @type {import('tailwindcss').Config} */

// npx tailwindcss -i ./src/main/resources/static/css/input.css -o ./src/main/resources/static/css/output.css --watch

module.exports = {
  content: ["./src/main/resources/**/*.{html,js,jsx,ts,tsx}"],
  theme: {
    extend: {},
  },
  plugins: [],
  darkMode: "selector", // Enable dark mode based on a class
};
