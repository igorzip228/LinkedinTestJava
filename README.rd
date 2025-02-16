1. Install NodeJS from https://nodejs.org/en/download
2. Add appium installer with command: npm install appium-installer -g
3. Install appium with appium-installer:
    - select "Needs help setting up Android Environment to run your Appium test?" and complete steps
    - select "Install appium server"
    - select "Install appium drivers", select options with SPACE
    - select "Install appium plugin"
    - appium -v
4. Download appium-inspector from https://github.com/appium/appium-inspector/releases or brew install --cask appium-inspector
    - alternatively you can use https://inspector.appiumpro.com/
5. Install Android Studio
6. Open Android Phone emulator
7. Start appium server in terminal run: appium --allow-cors --base-path /wd/hub --debug --relaxed-security
8. Add paths:
    - /usr/libexec/java_home
    - nano ~/.zshrc
    - add
        export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-18.0.1.1.jdk/Contents/Home
        export PATH=$JAVA_HOME/bin:$PATH"
    - source ~/.zshrc

9. Start Android emulator (pay attention to the setDeviceName("emulator-5554") - set you emulator)
10. Run tests




