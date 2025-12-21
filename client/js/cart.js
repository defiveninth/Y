// Cart management
function getCart() {
  const cartStr = localStorage.getItem("cart")
  return cartStr ? JSON.parse(cartStr) : []
}

function saveCart(cart) {
  localStorage.setItem("cart", JSON.stringify(cart))
}

function addToCart(flower, quantity = 1) {
  const cart = getCart()
  const existingItem = cart.find((item) => item.id === flower.id)

  if (existingItem) {
    existingItem.quantity += quantity
  } else {
    cart.push({ ...flower, quantity })
  }

  saveCart(cart)
  updateCartCount()
  return cart
}

function removeFromCart(flowerId) {
  const cart = getCart()
  const newCart = cart.filter((item) => item.id !== flowerId)
  saveCart(newCart)
  updateCartCount()
  return newCart
}

function updateCartItemQuantity(flowerId, quantity) {
  const cart = getCart()
  const item = cart.find((item) => item.id === flowerId)

  if (item) {
    item.quantity = Math.max(1, quantity)
    saveCart(cart)
    updateCartCount()
  }

  return cart
}

function clearCart() {
  localStorage.removeItem("cart")
  updateCartCount()
}

function getCartTotal() {
  const cart = getCart()
  return cart.reduce((sum, item) => sum + item.price * item.quantity, 0)
}

function updateCartCount() {
  const cart = getCart()
  const count = cart.reduce((sum, item) => sum + item.quantity, 0)
  const cartBadge = document.getElementById("cart-count")
  if (cartBadge) {
    cartBadge.textContent = count
    cartBadge.style.display = count > 0 ? "inline" : "none"
  }
}

// Initialize cart count on page load
document.addEventListener("DOMContentLoaded", updateCartCount)
