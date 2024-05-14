 @Override
    public void onLocationChanged(Location _location)
    {
        // 创建意图并添加位置数据
        locationAppIntent.putExtra("Location", _location);
        weatherIntent.putExtra("Location", _location);

        // 设置目标应用包名
        locationAppIntent.setPackage("tcs.lbs.locationapp"); // 仅本应用接收
        weatherIntent.setPackage("tcs.lbs.weather_app");     // 仅天气应用接收

        // 发送广播
        sendBroadcast(locationAppIntent);
        sendBroadcast(weatherIntent);
    }
