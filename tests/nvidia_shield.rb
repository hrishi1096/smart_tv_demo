require 'rubygems'
require 'appium_lib'
require 'pry'

time = Time.new
timestamp = time.strftime("%b-%d %H:%M:%S")

desired_caps = {
    'project': 'Smart TV Sales demo',
    'build': "Nvidia Shield TV Pro : #{timestamp}",
    'name': 'Nvidia Shield',
    'deviceName': 'Nvidia Shield TV Pro 2019',
    'osVersion': '11.0',
    'platformName': 'android',
    'app': 'AndroidApp',
    'browserstack.networkLogs': true,
    'browserstack.debug': true
}
appium_driver = Appium::Driver.new({
    'caps' => desired_caps,
    'appium_lib' => {
      :server_url => "https://#{ENV["BROWSERSTACK_USERNAME"]}:#{ENV["BROWSERSTACK_ACCESS_KEY"]}@hub-cloud.browserstack.com/wd/hub"
    }}, true)
driver = appium_driver.start_driver

wait = Selenium::WebDriver::Wait.new(:timeout => 30)
3.times do |i|
  next_button = wait.until { driver.find_element(:xpath, '//android.view.View[@content-desc="Next"]') }
  next_button.click
end
get_started_button = wait.until { driver.find_element(:id, "com.example.android.tvleanback:id/button_start") }
get_started_button.click
sleep 3
menu_sideitems = wait.until { driver.find_elements(:id, "com.example.android.tvleanback:id/header_label") }
menu_sideitems.first.click
# Select the video thumbnail
driver.press_keycode(22) # DPAD_RIGHT
driver.press_keycode(23) # DPAD_CENTER
sleep 2
# Click on Watch Trailer
driver.press_keycode(23) # Play
sleep 2
# Focus on media player
driver.press_keycode(23) # Focus
# Pause the video
driver.press_keycode(23) # Pause
sleep 2
# Play the video
driver.press_keycode(23) # Play
sleep 2
driver.press_keycode(4) # BACK
overview_panel = driver.find_elements(:id, "com.example.android.tvleanback:id/details_overview_actions")
if (overview_panel.size > 0)
  puts "Overview Panel Found - Tests Passed"
  driver.execute_script('browserstack_executor: {"action": "setSessionStatus", "arguments": {"status":"passed", "reason":"Overview Panel Found - Tests Passed"}}')
else
  puts "Overview Panel Not Found - Tests Failed"
  driver.execute_script('browserstack_executor: {"action": "setSessionStatus", "arguments": {"status":"failed", "reason":"Overview Panel Not Found - Tests Failed"}}')
end
driver.quit












