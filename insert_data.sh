#!/usr/bin/env bash

declare -A company

create_company () {
    local name=$1
    local sectorName=$2
    local numberOfEmployees=$3
    local revenues=$4
    local latitude=$5
    local longitude=$6
    local description=$7

    company[name]=$name
    company[sectorName]=$sectorName
    company[numberOfEmployees]=$numberOfEmployees
    company[revenues]=$revenues
    company[latitude]=$latitude
    company[longitude]=$longitude
    company[description]=$description
}

do_curl() {
    local url="http://localhost:8080/company"
    local data=$(echo "{
        \"name\":\"${company[name]}\",
        \"sectorName\":\"${company[sectorName]}\",
        \"numberOfEmployees\":${company[numberOfEmployees]},
        \"revenues\":${company[revenues]},
        \"location\":{
            \"latitude\":${company[latitude]},
            \"longitude\":${company[longitude]}
        },
        \"description\":\"${company[description]}\"
    }")

    local response=$(curl -s -o /dev/null -w "%{http_code}" -X POST \
        -H 'Content-Type: application/json' \
        -d "$data" \
        "$url")

    if [ "$response" -eq 201 ]; then
        printf "\e[32m[INFO] Company %s created\e[0m\n"  "${company[name]}"
    elif [ "$response" -eq 409 ]; then
        printf "\e[33m[WARNING] Company %s already exists\e[0m\n"  "${company[name]}"
    else
        printf "\e[31m[ERROR] Error creating %s company\e[0m\n" "${company[name]}"
    fi
}

create_sectors() {
    local url="http://localhost:8080/sector"
    local data=$(echo "{
        \"name\":\"${company[sectorName]}\"
    }")

    local response=$(curl -s -o /dev/null -w "%{http_code}" -X POST \
        -H 'Content-Type: application/json' \
        -d "$data" \
        "$url")

    if [ "$response" -eq 201 ]; then
        printf "\e[32m[INFO] Sector %s created\e[0m\n" "${company[sectorName]}"
    elif [ "$response" -eq 409 ]; then
        printf "\e[33m[WARNING] Sector %s already exists\e[0m\n" "${company[sectorName]}"
    else
        printf "\e[31m[ERROR] Error %s creating sector\e[0m\n" "${company[sectorName]}"
    fi
}

companies=("Accenture:Consultoría tecnológica:5000:4000:36.73449628909517:-4.557323263764723:Empresa multinacional de consultoría, servicios tecnológicos y outsourcing." 
          "Dekra:Inspección y certificación:35000:3300:36.73609939452302:-4.553789041168187:Empresa multinacional de inspección, certificación y ensayos."
          "Ericsson:Telecomunicaciones:100000:27000:36.73881867629296:-4.553659175588572:Empresa multinacional de inspección, certificación y ensayos."
          "Huawei:Tecnología de la información:195000:136000:36.734200895748494:-4.555803261141127:Empresa multinacional de tecnología de la información que ofrece una amplia gama de productos y servicios."
          "Indra:Tecnología de la información:350000:73600:36.73545603306634:-4.557705769574389:Empresa multinacional de tecnología de la información que ofrece una amplia gama de productos y servicios."
          "NTT DATA:Servicios informáticos:130000:16000:36.7186571026646:-4.422429106392507:Empresa multinacional de tecnología de la información que ofrece una amplia gama de productos y servicios."
          "Oracle:Servicios informáticos:135000:39000:36.73982879811848:-4.554063731854873:Empresa multinacional de software que desarrolla y comercializa una amplia gama de productos de software.")

printf "\e[32m+++ Inserting data into the database\e[0m\n"
for company_info in "${companies[@]}"; do
    IFS=':' read -r -a company_data <<< "$company_info"
    create_company "${company_data[@]}"

    create_sectors

    do_curl
done
