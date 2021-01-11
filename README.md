[![Download](https://api.bintray.com/packages/nikart/maven/ImageBadgeView/images/download.svg)](https://bintray.com/nikart/maven/ImageBadgeView/_latestVersion) [![Release](https://jitpack.io/v/nikartm/image-support.svg)](https://jitpack.io/#nikartm/image-support) [![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-ImageBadgeView-green.svg?style=flat )]( https://android-arsenal.com/details/1/7619) [![Donate using PayPal](https://img.shields.io/badge/paypal-donate-blue.svg)](https://www.paypal.me/ivodyasov)

# ImageBadgeView
Library to add ImageView (ImageBadgeView) with a badge like notification count.
## Download
Add to gradle root:
```
buildscript {
    repositories {
        jcenter()
    }
}
```
Download via Gradle:
```
implementation 'com.github.nikartm:image-support:$LAST_VERSION'
```

## Screenshots
![BarcodeInfo Screenshots](https://raw.githubusercontent.com/nikartm/Image-Support/master/screenshots/sct_1.png)
## How to use?
Adjust the xml view [More examples.](https://github.com/nikartm/Image-Support/blob/master/app/src/main/res/layout/activity_main.xml):
```
<ru.nikartm.support.ImageBadgeView
    android:id="@+id/ibv_icon2"
    android:layout_width="80dp"
    android:layout_height="80dp"
    android:layout_marginTop="30dp"
    android:layout_gravity="center"
    android:padding="10dp"
    app:ibv_badgeValue="100"
    app:ibv_badgeTextSize="12sp"
    app:ibv_fixedBadgeRadius="15dp"
    app:ibv_badgeTextStyle="bold"
    app:ibv_badgeTextColor="#ffffff"
    app:ibv_badgeColor="#00ACC1"
    app:ibv_badgeLimitValue="false"
    android:src="@drawable/ic_shopping_cart" />
```
Or programmatically:
```
imageBadgeView.setBadgeValue(27)
        .setBadgeOvalAfterFirst(true)
        .setBadgeTextSize(16)
        .setMaxBadgeValue(999)
        .setBadgeTextFont(typeface)
        .setBadgeBackground(getResources().getDrawable(R.drawable.rectangle_rounded))
        .setBadgePosition(BadgePosition.BOTTOM_RIGHT)
        .setBadgeTextStyle(Typeface.NORMAL)
        .setShowCounter(true)
        .setBadgePadding(4);
```
Change the position of a badge on view:
```
app:ibv_badgePosition="bottom_right"
```
Use the custom badge background:
```
app:ibv_badgeBackground="@drawable/rectangle_rounded"
```
Example the custom background - rectangle_rounded.xml:
```
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">

    <solid android:color="#ea5444"/>
    <corners android:radius="24dp"/>

    <stroke
        android:width="1dp"
        android:color="#f5f5f5"/>
</shape>
```

## License
Copyright 2018 Ivan Vodyasov

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.