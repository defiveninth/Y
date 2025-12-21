const API_URL = "http://localhost:3000/api"

// Get token from localStorage
function getToken() {
  return localStorage.getItem("token")
}

// Get current user from localStorage
function getCurrentUser() {
  const userStr = localStorage.getItem("user")
  return userStr ? JSON.parse(userStr) : null
}

// Set auth data
function setAuthData(token, user) {
  localStorage.setItem("token", token)
  localStorage.setItem("user", JSON.stringify(user))
}

// Clear auth data
function clearAuthData() {
  localStorage.removeItem("token")
  localStorage.removeItem("user")
  localStorage.removeItem("cart")
}

// API call helper
async function apiCall(endpoint, options = {}) {
  const token = getToken()
  const headers = {
    "Content-Type": "application/json",
    ...(token && { Authorization: `Bearer ${token}` }),
    ...options.headers,
  }

  const response = await fetch(`${API_URL}${endpoint}`, {
    ...options,
    headers,
  })

  if (!response.ok) {
    const error = await response.json()
    throw new Error(error.error || "Something went wrong")
  }

  return response.json()
}

// Auth API
const authAPI = {
  register: (data) =>
    apiCall("/auth/register", {
      method: "POST",
      body: JSON.stringify(data),
    }),

  login: (data) =>
    apiCall("/auth/login", {
      method: "POST",
      body: JSON.stringify(data),
    }),
}

// Flowers API
const flowersAPI = {
  getAll: () => apiCall("/flowers"),

  create: (data) =>
    apiCall("/flowers", {
      method: "POST",
      body: JSON.stringify(data),
    }),

  update: (id, data) =>
    apiCall(`/flowers/${id}`, {
      method: "PUT",
      body: JSON.stringify(data),
    }),

  delete: (id) =>
    apiCall(`/flowers/${id}`, {
      method: "DELETE",
    }),
}

// Orders API
const ordersAPI = {
  create: (data) =>
    apiCall("/orders", {
      method: "POST",
      body: JSON.stringify(data),
    }),

  getMyOrders: () => apiCall("/orders/my-orders"),

  getAll: () => apiCall("/orders"),

  updateStatus: (id, status) =>
    apiCall(`/orders/${id}/status`, {
      method: "PATCH",
      body: JSON.stringify({ status }),
    }),
}
