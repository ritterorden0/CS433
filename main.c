#include <stdio.h>
#include <stdlib.h>

int main(int argc, char* argv[])
{
    FILE* fp = fopen(argv[1], "r");
    char c;
    while (c=fgetc(fp) != EOF) {

    }
    return 0;
}
