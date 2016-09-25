# Hometask for Odnoklassniki.ru

## `Table of contents`

* [Used tools](#used-tools)
* [Browsers](#browsers)
* [Run Functional Testing (Console)](#run-functional-testing-console)
* [View Report](#view-report)
* [IDE](#ide)
* [Required Enhancements](#required-enhancements)
* [Possible Evolution](#possible-evolution)
    
## `Used tools`
* Maven - version 3.3.9
* Java - version 1.8
* Spring - version 4.1.1
* Cucumber - version 1.2.4
* Selenium WebDriver - version 2.49.1
* Yandex Allure for reporting - version 2.5
* [up](#table-of-contents)

## `Browsers`
* Developed with the Firefox 41.0.2 - **there is no guarantee that the later version would work fine**
* [up](#table-of-contents)

## `Code Structure`
* Maven modules are used:
    * **ok-qa-module** - all the QA code is located here
        * **ok-qa-configs** - all the configs (user properties, log configs, etc.) are located here
        * **ok-qa-services** - all the backend QA code is located here
            * **ok-qa-services-webdriver-utils** - working with Selenium WebDriver
            * **ok-qa-services-page-objects** - Page Objects for interacting with the app
            * **ok-qa-services-reporting** - reports collectors
        * **ok-qa-testing-functional** - all the functional tests logic and implementation is stored here
* Configs
    * In the module **ok-qa-services-webdriver-utils**: ru.ok.rmatafonov.config.WebDriverConfiguration
    * Main Functional Testing Config - in the module **ok-qa-testing-functional**: ru.ok.rmatafonov.functest.config.TestingConfig
* [up](#table-of-contents)
        
## `Run Functional Testing (Console)`
* **Required Maven 3.1.1 and higher**
* Define environment variables (**could be skipped if you'd like to run tests in Firefox on Production for Russian language**)
    * Define environment variable BROWSER to user required browser (case insensible):
        * For Firefox browser: firefox or ff (**used by default**)
        * For Chrome browser: chrome or ch
        * For Internet Explorer browser: internet explorer, internetexplorer, ie
        * Please add other browsers support in **ok-qa-services-webdriver-utils** if necessary 
    * Define environment variable APP_ENV to use required environment:
        * Production: prod (**used by default**)
        * Please add other configs support in **ok-qa-configs** if necessary 
    * Define environment variable TESTED_LANGUAGE for localisation:
        * Russian: ru (**used by default**)
        * Please add other configs support in **ok-qa-configs** if necessary 
* Navigate to the directory of the Maven module **ok-rmatafonov-hometask** (root)
* Execute the command:
    * _mvn clean install -Dmaven.test.skip=true_
* Navigate to the directory of the Maven module **ok-qa-testing-functional**
* Execute the command:
    * _mvn clean install_
* [up](#table-of-contents)
    
## `View Report`
* A Yandex Allure report is generated automatically after maven run in the module **ok-qa-testing-functional**
* The report is located in the directory of the module **ok-qa-testing-functional** inside ./target/site/allure-maven-plugin
* **Some web service is required to display the report**, 
* **E.g. Intellij IDEA provides such one (just open index.html and click a browser icon in the right top corner)**
* [up](#table-of-contents)

## `IDE`
* It's highly recommended to use Intellij IDEA of version 2016.2.4 and higher
* For being able to easily work with Cucumber Gherkin scenarios it's recommended to set up the plugins:
    * Cucumber for Java
    * Gherkin
* [up](#table-of-contents)
    
## `Required Enhancements`
* Add support for DB or some service for getting data (to avoid getting it from GUI)
* Add local set of symbols into the appropriate property file and add it's support into testing config
* [up](#table-of-contents)
        
## `Possible Evolution`
* Another types of testing could be added by adding new Maven modules:
    * E.g. **ok-qa-testing-smoke**, **ok-qa-testing-performance**, etc.
* Another app Page Objects could be added by adding new Maven modules:
    * E.g. **mailru-qa-services-page-objects**
* The ideal situation - to separate the module **ok-qa-services** into an independent project
* Actually the modulated architecture allows to enhance this system as needed
* [up](#table-of-contents)

