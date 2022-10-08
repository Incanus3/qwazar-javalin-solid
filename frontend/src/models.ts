export type Viewset = {
    name: string,
    views: Record<string, View>,
}

export type View = {
    name: string,
}

export type WidgetData = {
    type: string,
}
