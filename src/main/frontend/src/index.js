import React, { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import './globals.css';
import App from "./App";
import Cart from "./pages/carrinho/Cart";
import { createBrowserRouter, RouterProvider } from "react-router-dom";

const router = createBrowserRouter([
  {
    path: "/", 
    element: <App/>,
  },
  {
    path: "/cart",
    element: <Cart/>,
  },
])

const root = createRoot(document.getElementById("root"));
root.render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>
);