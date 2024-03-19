import React, { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import './globals.css';
import App from "./App";
import Info from "./pages/sobre/Info";
import Form from "./pages/contato/Form";
import { createBrowserRouter, RouterProvider } from "react-router-dom";

const router = createBrowserRouter([
  {
    path: "/", 
    element: <App/>,
  },
  {
    path: "/info",
    element: <Info/>,
  },
  {
    path: "/contact",
    element: <Form/>,
  },
])

const root = createRoot(document.getElementById("root"));
root.render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>
);