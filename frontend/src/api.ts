// generic utils

import { Viewset } from "./models"
import { WidgetProps } from "./widgets"

function sleep(millis: number) {
  return new Promise(resolve => setTimeout(resolve, millis))
}

async function api<T>(url: string) {
  await sleep(500)
  const response = await fetch(url)
  return await response.json() as T
}

// viewsets and views

export function getViewsets() {
  return api<Viewset[]>("http://localhost:7070/viewsets")
}

export function getView(viewset: Viewset, viewName: string) {
  return api<WidgetProps>(`http://localhost:7070/viewsets/${viewset.name}/views/${viewName}`)
}
