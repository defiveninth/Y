// Auth state management
function getCurrentUser() {
  const userStr = localStorage.getItem("user")
  return userStr ? JSON.parse(userStr) : null
}

function setAuthData(token, user) {
  localStorage.setItem("token", token)
  localStorage.setItem("user", JSON.stringify(user))
}

function clearAuthData() {
  localStorage.removeItem("token")
  localStorage.removeItem("user")
  localStorage.removeItem("cart")
}

// Check if user is logged in
function isLoggedIn() {
  return !!getCurrentUser()
}

// Check if user is admin
function isAdmin() {
  const user = getCurrentUser()
  return user && user.role === "admin"
}

// Logout
function logout() {
  clearAuthData()
  window.location.href = "/login.html"
}

// Update navigation based on auth status
function updateNavigation() {
  const authNav = document.getElementById("auth-nav")
  if (!authNav) return

  const user = getCurrentUser()

  if (user) {
    authNav.innerHTML = `
    <div class="d-flex align-items-center gap-2">
      ${user.role === "admin" ? '<a class="btn btn-sm btn-outline-secondary" href="/admin.html">Admin</a>' : ""}
      <a class="btn btn-sm btn-outline-secondary" href="/orders.html">Orders</a>
      <a class="btn btn-sm btn-outline-danger" href="#" onclick="logout()">Logout</a>
    </div>
  `
  } else {
    authNav.innerHTML = `
    <div class="d-flex gap-2">
      <a class="btn btn-sm btn-outline-secondary" href="/login.html">Login</a>
      <a class="btn btn-sm btn-danger" href="/register.html">Register</a>
    </div>
  `
  }
}

// Protect page (redirect if not logged in)
function requireAuth() {
  if (!isLoggedIn()) {
    window.location.href = "/login.html"
    return false
  }
  return true
}

// Protect admin page
function requireAdmin() {
  if (!isAdmin()) {
    alert("Access denied. Admin only.")
    window.location.href = "/index.html"
    return false
  }
  return true
}

// Initialize auth navigation on page load
document.addEventListener("DOMContentLoaded", updateNavigation)
