console.log("Script Loaded");

let currentTheme = getTheme();

document.addEventListener("DOMContentLoaded", function () {
  toggleTheme();
});

// Set the theme on page load
changePageTheme(currentTheme);

function toggleTheme() {
  const changeThemeButton = document.querySelector("#theme_change_button");
  changeThemeButton.addEventListener("click", function () {
    const oldTheme = currentTheme;
    console.log("Theme Button Clicked");
    // Toggle the theme
    currentTheme = currentTheme === "light" ? "dark" : "light";
    setTheme(currentTheme);
    changePageTheme(currentTheme, oldTheme);

    changeThemeButton.querySelector("span").textContent =
      currentTheme === "light" ? "Dark" : "Light";
  });
}

// Change the theme on the page
function changePageTheme(newTheme, oldTheme) {
  const html = document.querySelector("html");
  if (oldTheme) html.classList.remove(oldTheme);
  html.classList.add(newTheme);
}

// Set theme to local storage
function setTheme(theme) {
  localStorage.setItem("theme", theme);
}

// Get theme from local storage
function getTheme() {
  let theme = localStorage.getItem("theme");
  return theme ? theme : "light";
}
