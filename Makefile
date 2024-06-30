
LOCAL_ADDRESS := "http://localhost:8080"

run: # To Start Application
	@echo "Starting application at ${LOCAL_ADDRESS}"
	@mvn spring-boot:run


start: # To Launch Browser with Example Query
	@echo "launching browser for address ${LOCAL_ADDRESS}"
	open ${LOCAL_ADDRESS}/romannumeral?query=2200000000

test: # To Run Tests for application
	@echo "Running Tests"
	@mvn test

build: # Install Dependencies
	@echo "Building and Testing App..."
	@mvn clean verify

metric: # To list accessible app Metrics
	@echo "launching browser for metrics"
	@open ${LOCAL_ADDRESS}/actuator/metrics


metric-cpu: # To see system cpu usage
	@echo "launching browser for metric - system cpu usage"
	@open ${LOCAL_ADDRESS}/actuator/metrics/system.cpu.usage

generate-report: # Generate Report
	@echo "Generating Test Report"
	@mvn jacoco:report

open-report: # Open - Generated Report
	@echo "Opening Report"
	@open target/site/jacoco/index.html