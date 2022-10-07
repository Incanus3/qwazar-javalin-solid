// generic utils

import { Viewset } from "./models"

function sleep(millis: number) {
  return new Promise(resolve => setTimeout(resolve, millis))
}

async function api<T>(url: string) {
  await sleep(500)
  const response = await fetch(url)
  return await response.json() as T
}

// viewsets

export function getViewsets() {
  return api<Viewset[]>("http://localhost:7070/viewsets")
}
