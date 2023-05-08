require 'rubygems'
require 'appium_lib'
require 'pry'

time = Time.new
timestamp = time.strftime("%b-%d %H:%M:%S")

desired_caps = {
    'project': 'Smart TV Sales demo',
    'build': "Appium Apple TV : #{timestamp}",
    'name': 'Apple tv',
    'deviceName': 'Apple TV 4k',
    'osVersion': '16.3',
    'platformName': 'tvos',
    'automationName': 'xcuitest',
    'app': 'bs://9384096bb99037d549fced7f06923a7eeaebe7ba',
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

driver.screenshot_as(:base64)

driver.execute_script('browserstack_executor: {"action": "setSessionStatus", "arguments": {"status":"passed", "reason":"Homepage screenshot taken - Test Passed"}}')
puts "Homepage screenshot taken - Test Passed"

driver.quit












