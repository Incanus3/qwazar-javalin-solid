import { Link, Route, Router, Routes } from '@solidjs/router';
import { Component, createResource, For, Show } from 'solid-js';
import { getViewsets } from './api';
import { Viewset } from './models';

const View: Component<{ viewset: string, view: string }> = (props) => {
  return <p>rendering view {props.view} of viewset {props.viewset}</p>
}

const ViewRoutes: Component<{ viewsets: Viewset[] }> = (props) => {
  const viewRoute = (viewset: Viewset, viewName: string) =>
    <Route path={viewName} element={<View viewset={viewset.name} view={viewName} />} />

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
  const link = (path: string) => <Link href={path}>{path}</Link>

  return (
    <ul>
      <li>{link('/')}</li>
      <For each={props.viewsets}>{(viewset) =>
        <For each={Object.keys(viewset.views)}>{(viewName) =>
          <li>{link(`/${viewset.name}/${viewName}`)}</li>
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
