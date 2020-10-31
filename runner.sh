#!/usr/bin/env bash
# POSIX

PROJECT_HOME=$(pwd)

SERVICE="project memphis"
verbose=0

dev_runner()
{
    echo "running $SERVICE in development mode .."
     ./gradlew clean :core:bootRun -Dspring.profiles.active=dev --no-daemon

}

dev_runnerWithLoadData()
{
    echo "running $SERVICE in development mode(test data will be loaded) .."
    ./gradlew clean :core:bootRun -Dspring.profiles.active=dev -DloadData=true --no-daemon
}

test_runner()
{
    echo "running $SERVICE in test mode .."
    ./gradlew clean test --no-daemon
}

init_runner()
{
    echo "Initializing project .."
    cd ./docker/
    docker-compose kill
    docker-compose rm -f -v
    docker-compose up --build -d
    cd $PROJECT_HOME
}

prod_runner()
{
    echo "running $SERVICE in prod mode .."


}
stop_runner()
{
    echo "Stopping project"
    cd ./docker/
    docker-compose kill
    cd $PROJECT_HOME
    exit 1
}

show_help()
{
cat << EOF
      Running the $SERVICE is available in the following modes:
           --init      initializes the development infrastructure by building the necessary containers
           --test      runs the application for testing and runs all the unit, integration and end-to-end tests
           --dev       runs the application in development mode
           --prod      runs the application in production mode (inside a docker container, check the ENVS!)
           --v         for enabling the verbose tracing of commands execution
EOF
}

die() {
    printf '%s\n' "$1" >&2
    exit 1
}

while :; do
    case $1 in
        -h|-\?|--help)
            show_help    # Display a usage synopsis.
            exit
            ;;
        init|-init|-\?|--init)
            init_runner
            exit
            ;;
        stop|-stop|-\?|--stop)
            stop_runner
            exit
            ;;
        dev|-dev|-\?|--dev)
            if [ "$2" == "loadData" ]; then
                dev_runnerWithLoadData;
            else
                dev_runner;
            fi
            exit
            ;;
        test|-test|-\?|--test)
            test_runner
            exit
            ;;
         prod|-prod|-\?|--prod)
            prod_runner
            exit
            ;;
        -v|--verbose)
            verbose=$((verbose + 1))  # Each -v adds 1 to verbosity.
            ;;
        --)
            shift
            break
            ;;
        *|-?*)
            printf 'WARN: Unknown option (ignored): %s\n' "$1" >&2
            show_help
            break
            ;;
        *)               # Default case: No more options, so break out of the loop.
            break
    esac

    shift
done
