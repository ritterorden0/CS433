#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#define FRAME_SIZE 256
#define MAX_FRAMES 256
#define ADDR_MASK 0xFFFF
#define OFFSET_MASK 0xFF
#define TLB_SIZE 16
#define PAGE_TABLE_SIZE 256
#define BUF_SIZE 10
#define BYTES 256

int pageNumTable[PAGE_TABLE_SIZE];
int pageFrameTable[PAGE_TABLE_SIZE];

int TLBPageNum[TLB_SIZE];
int TLBFrameNum[TLB_SIZE];

int physMem[MAX_FRAMES][FRAME_SIZE];

int pageFaults = 0;
int TLBHits = 0;
int firstAvailableFrame = 0;
int firstAvailablePageNum = 0;
int numTLBEntries = 0;

FILE *addr_file;
FILE *backing_store;

char addr[BUF_SIZE];
int logicalAddr;
signed char buffer[BYTES];
signed char value;

void getPage(int addr);
void readFromStore(int pageNum);
void insertInTLB(int pageNum, int frameNum);

void getPage(int addr) {
    int pageNum = ((addr & ADDR_MASK)>>8);
    int offset = (addr & OFFSET_MASK);

    int frameNum = -1;

    for (int i = 0; i < TLB_SIZE; i++) {
        if (TLBPageNum[i] == pageNum) {
            frameNum = TLBFrameNum[i];
            TLBHits++;
        }
    }

    if (frameNum == -1) {
        for (int i = 0; i < firstAvailablePageNum; i++) {
            if (pageNumTable[i] == pageNum) {
                frameNum = pageFrameTable[i];
            }
        }
        if (frameNum == -1) {
            readFromStore(pageNum);
            pageFaults++;
            frameNum = firstAvailableFrame - 1;
        }
    }
}

void readFromStore(int pageNum) {
    if (fseek(backing_store, pageNum * BYTES, SEEK_SET) != 0) {
        fprintf(stderr, "Error seeking in backing store.\n");
    }
}
