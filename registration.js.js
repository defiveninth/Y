function validateForm() {

  let valid = true;

  // Username
  let username = document.getElementById("username").value;
  if (username.length < 2) {
    document.getElementById("userError").textContent = "Name must be at least 2 characters!";
    valid = false;
  } else {
    document.getElementById("userError").textContent = "";
  }

  // Passwords
  let p1 = document.getElementById("pass1").value;
  let p2 = document.getElementById("pass2").value;

  if (p1.length === 0) {
    document.getElementById("passError1").textContent = "Enter password!";
    valid = false;
  } else {
    document.getElementById("passError1").textContent = "";
  }

  if (p1 !== p2) {
    document.getElementById("passError2").textContent = "Passwords do not match!";
    valid = false;
  } else {
    document.getElementById("passError2").textContent = "";
  }

  // Birth year + age check
  let year = document.getElementById("year").value;
  let currentYear = new Date().getFullYear();
  let age = currentYear - year;

  if (age < 18) {
    document.getElementById("yearError").textContent = "You must be at least 18!";
    valid = false;
  } else {
    document.getElementById("yearError").textContent = "";
  }

  // Photo
  let photo = document.getElementById("photo").files.length;
  if (photo === 0) {
    document.getElementById("photoError").textContent = "Upload a photo!";
    valid = false;
  } else {
    document.getElementById("photoError").textContent = "";
  }

  // About user
  let about = document.getElementById("about").value.trim().split(/\s+/);
  if (about.length < 30) {
    document.getElementById("aboutError").textContent = "Description must contain at least 30 words!";
    valid = false;
  } else {
    document.getElementById("aboutError").textContent = "";
  }

  // If all correct → show success message
  if (valid) {
    alert("Registration successful!");
  }

  return false; // Prevent form reload for this assignment
}
// скрипт: вывести теги
const allTags = document.querySelectorAll("*");
allTags.forEach(tag => console.log(tag.tagName));

function updatePage() {  }
function changeStyles() {  }

