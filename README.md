# FPCameraProxy

The stock camera app of a Fairphone 4 (FPCamera) relies on Google Photos to be installed.
Pressing the round preview icon shall open a picture preview in Google Photos.
If, however, someone does not want to use Google Photos, the camera app throws an error.
This simple app pretends to be Google Photos by using its package name.
When receiving an intent for Google Photos, it creates a new intent copying everything
but the designated package name. This triggers a system dialogue asking the proper galery app. 

Since it uses the same package name as Google Photos, any app store will assume Google Photos
is installed, and informs about an update if present.

This app inspired by [GsfProxy](https://github.com/microg/android_packages_apps_GsfProxy).
