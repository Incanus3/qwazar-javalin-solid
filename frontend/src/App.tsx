import { Link, Route, Router, Routes } from '@solidjs/router';
import { Component, createResource, ErrorBoundary, For, Show } from 'solid-js';
import { getView, getViewsets } from './api';
import { Viewset } from './models';
import { LinkWidget, renderWidget, WidgetProps } from './widgets';

const View: Component<{ viewset: Viewset, viewName: string }> = (props) => {
  const [data] = createResource(() => getView(props.viewset, props.viewName))

  return (
    <ErrorBoundary
      fallback={(err, reset) => <div onClick={reset}>{err.toString()}</div>}
    >
      <p>rendering view {props.viewName} of viewset {props.viewset.name}</p>
      <Show when={!data.loading} fallback={<p>načítám</p>}>
        {renderWidget(data() as WidgetProps)}
      </Show>
    </ErrorBoundary >
  )
}

const ViewRoutes: Component<{ viewsets: Viewset[] }> = (props) => {
  const viewRoute = (viewset: Viewset, viewName: string) =>
    <Route path={viewName} element={<View viewset={viewset} viewName={viewName} />} />

  return (
    <Routes>
      <Route path="/" element={<p>rendering root page</p>} />
      <For each={props.viewsets}>{(viewset) =>
        <Route path={viewset.name}>
          <For each={Object.keys(viewset.views)}>{(viewName) =>
            viewRoute(viewset, viewName)
          }</For>
        </Route>
      }</For>
    </Routes>
  )
}

const ViewLinks: Component<{ viewsets: Viewset[] }> = (props) => {
  return (
    <ul>
      <li><Link href="/">/</Link></li>
      <For each={props.viewsets}>{(viewset) =>
        <For each={Object.keys(viewset.views)}>{(viewName) =>
          <li><LinkWidget viewsetName={viewset.name} viewName={viewName} /></li>
        }</For>
      }</For>
    </ul>
  )
}

const App: Component = () => {
  const [data] = createResource(getViewsets)
  const viewsets = () => data() ?? []

  return (
    <Show when={!data.loading} fallback={<p>načítám</p>}>
      {JSON.stringify(data())}

      <Router>
        <ViewLinks viewsets={viewsets()} />
        <ViewRoutes viewsets={viewsets()} />
      </Router>
    </Show>
  );
};

export default App;
