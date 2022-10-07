export type View = {
  name: string,
}
export type Viewset = {
  name: string,
  views: Record<string, View>,
}
