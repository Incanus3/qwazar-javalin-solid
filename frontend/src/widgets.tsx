import { Link } from "@solidjs/router"
import { Component } from "solid-js"

export interface WidgetProps {
    type: string,
}

interface TextProps extends WidgetProps { content: string }
interface LinkProps extends WidgetProps { viewsetName: string, viewName: string }
interface ButtonProps extends WidgetProps { label: string }

export const TextWidget: Component<TextProps> = (props) => <p>{props.content}</p>
export const ButtonWidget: Component<ButtonProps> = (props) => <button>{props.label}</button>

export const LinkWidget: Component<{ viewsetName: string, viewName: string }> = (props) => {
  const path = `/${props.viewsetName}/${props.viewName}`
  return <Link href={path}>{path}</Link>
}

export const renderWidget = (widget: WidgetProps) => {
  switch (widget.type) {
    case 'text': return <TextWidget {...widget as TextProps} />
    case 'link': return <LinkWidget {...widget as LinkProps} />
    case 'button': return <ButtonWidget {...widget as ButtonProps} />
    default: throw new Error(`unknown widget type ${widget.type}`)
  }
}
