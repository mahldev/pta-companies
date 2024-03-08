insert_data:
	@./insert_data.sh

clean_db:
	@printf "\e[32m+++ Cleaning the database\e[0m\n"
	@docker compose down && ./gradlew bootRun

run:
	@printf "\e[32m+++ Running the application\e[0m\n"
	@./gradlew bootRun -q >> /dev/null &

stop:
	@printf "\e[32m+++ Stopping the application\e[0m\n"
	@./gradlew --stop >> /dev/null &
