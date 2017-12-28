Framework structure:
	1. Esclipe: Java IDE
	2. Maven: software project management (manage a project's build, reporting and documentation)
	3. TestNG: testing framework
	4. Test runner: Maven failed-safe plugin with TestNG xml suite
	5. Selenium: Web automation framework tool

Dependencies:
	1. Should add PATH to browser driver folder
	2. If IE does not work, Try going to Internet Options --> Security --> "Enable Protected Mode" on ALL zones should either be 
	checked	or ALL unchecked.
	3. Screen shoot for failed case is store in target\failedScreenshoot

- Webdriver vs chromedriver: https://stackoverflow.com/questions/39378442/webdriver-vs-chromedriver