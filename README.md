# UnderActivity

## Javadoc

[Javadoc](http://rawgit.com/ydrojen/underactivity/master/javadoc/index.html)

# Usage Example

## Simple Activity

A simple Activity created from custom layout resource.

```java
@Override protected Builder configureActivityBuilder(Builder builder) {
  return builder.setContentLayoutResource(R.layout.custom_layout);
}
```

## Fragment Activity

An Activity hosting one fragment.

```java
@Override protected Builder configureActivityBuilder(Builder builder) {
  return builder;
}

@Override public void onPostCreate(Bundle savedInstanceState) {
  super.onPostCreate(savedInstanceState);
  setFragment(new FragmentExample());
}
```

## NavigationView Activity

An Activity who has a NavigationView drawer.

```java
@Override protected Builder configureActivityBuilder(Builder builder) {
  return builder.setContentLayoutResource(R.layout.custom_layout)
      .setDrawerMenuResource(R.menu.drawer_menu)
      .setDrawerHeaderResource(R.layout.header_navigation_drawer)
      .setDrawerOnNavigationItemSelectedListener(this);
}
```

## NavigationDrawer Activity

An Activity who has a Navigation Drawer inflated from a custom layout resource.

```java
@Override protected Builder configureActivityBuilder(Builder builder) {
  return builder.setContentLayoutResource(R.layout.custom_layout)
      .setDrawerCustomLayoutResource(R.layout.custom_drawer);
}
```

## Toolbar Activity

An Activity with default Toolbar.

```java
@Override protected Builder configureActivityBuilder(Builder builder) {
  return builder.setContentLayoutResource(R.layout.custom_layout)
      .enableToolbar(true).setToolbarBackgroundColor(getResources().getColor(R.color.accentColor));
}
```

## Custom Toolbar Activity

An Activity with Toolbar inflated from layout.

```java
@Override protected Builder configureActivityBuilder(Builder builder) {
  return builder.setContentLayoutResource(R.layout.custom_layout)
      .setToolbarResource(R.layout.custom_toolbar);
}
```

## Toolbar + Drawer Activity

Combine custom layout, NavigationView drawer and Toolbar. There are multiple configurations you can try for an Activity. Now do it yourself!

```java
@Override protected Builder configureActivityBuilder(Builder builder) {
  return builder.setContentLayoutResource(R.layout.custom_layout)
      .setToolbarBackgroundColor(getResources().getColor(R.color.accentColor))
      .setDrawerHeaderResource(R.layout.header_navigation_drawer)
      .setDrawerMenuResource(R.menu.drawer_menu)
      .setDrawerOnNavigationItemSelectedListener(this)
      .setToolbarDrawerIcon(R.drawable.ic_menu);
}
```
