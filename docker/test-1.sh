#!/bin/bash

cnt=2

echo -e "\n# Test IP HTTP"
for ((i=1; i<=cnt; i++))
do
    curl -s -L --max-time 2 http://144.22.131.24/api/info
    curl -s -L --max-time 2 http://144.22.131.24/api/actuator/health
    #curl -s -L --max-time 2 http://132.226.251.107/rt-3.html | grep "ChatBot RT"
    echo ""
done

echo -e "\n# Test hostname HTTP"
for ((i=1; i<=cnt; i++))
do
    curl -s -L --max-time 2 http://www.brunobcp.com/api/info
    curl -s -L --max-time 2 http://www.brunobcp.com/api/actuator/health
    curl -s -L --max-time 2 http://the.brunobcp.com/api/actuator/health
    #curl -s -L --max-time 2 http://rt.brunobcp.com/rt-3.html | grep "ChatBot RT"
    echo ""
done

echo -e "\n# Test IP HTTPS (ssl)"
for ((i=1; i<=cnt; i++))
do
    curl -s -L -k --max-time 2 https://144.22.131.24/api/info
    curl -s -L -k --max-time 2 https://144.22.131.24/api/actuator/health
    #curl -s -L --max-time 2 https://132.226.251.107/rt-3.html | grep "ChatBot RT"
    echo ""
done

echo -e "\n# Test hostname HTTPS (ssl)"
for ((i=1; i<=cnt; i++))
do
    curl -s -L --max-time 2 https://www.brunobcp.com/api/info
    curl -s -L --max-time 2 https://www.brunobcp.com/api/actuator/health
    curl -s -L -k --max-time 2 https://the.brunobcp.com/api/actuator/health
    #curl -s -L --max-time 2 https://rt.brunobcp.com/rt-3.html | grep "ChatBot RT"
    echo ""
done
