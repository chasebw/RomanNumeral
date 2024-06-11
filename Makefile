
LOCAL_ADDRESS := "http://localhost:8080"

run: # To Start Application
	@echo "Starting application at ${LOCAL_ADDRESS}"
	@mvn spring-boot:run


start: # To Launch Browser with Example Query
	@echo "launching browser for address ${LOCAL_ADDRESS}"
	open ${LOCAL_ADDRESS}/romannumeral?query=22

test: # To Run Tests for application
	@echo "Running Tests"
	@mvn test
