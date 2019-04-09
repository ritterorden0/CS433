#include <stdio.h>
#include <stdlib.h>

#define NUMBER_OF_CUSTOMERS 5
#define NUMBER_OF_RESOURCES 4

int available[NUMBER_OF_RESOURCES];
int max[NUMBER_OF_CUSTOMERS][NUMBER_OF_RESOURCES];
int alloc[NUMBER_OF_CUSTOMERS][NUMBER_OF_RESOURCES];
int need[NUMBER_OF_CUSTOMERS][NUMBER_OF_RESOURCES];
int request[NUMBER_OF_RESOURCES];
int release[NUMBER_OF_RESOURCES];

int request_resources(int customer_num, int request[]) {

    return 0;
}

void release_resources(int customer_num, int release[]) {
}

int isSafe() {
    if () {
        return 0;
    }
    return 1;
}
