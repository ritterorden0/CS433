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

//--------PAGE TABLES-------------
int pageNumTable[PAGE_TABLE_SIZE];
int pageFrameTable[PAGE_TABLE_SIZE];

//-------TLB TABLES--------------
int TLBPageNum[TLB_SIZE];
int TLBFrameNum[TLB_SIZE];

//-------PHYSICAL MEMORY-----------
int physMem[MAX_FRAMES][FRAME_SIZE];

int pageFaults = 0;
int TLBHits = 0;
int firstAvailableFrame = 0;
int firstAvailablePageNum = 0;
int numTLBEntries = 0;

//-----------FILES-------------------
FILE *addr_file;
FILE *backing_store;

char addr[BUF_SIZE];
int logicalAddr;

signed char buffer[BYTES]; //read backing buffer
signed char value; //bit value

//------------FUNCTIONS-------------------
void getPage(int addr);
void readFromStore(int pageNum);
void insertInTLB(int pageNum, int frameNum);
void print(int transNum);

//--------------MAIN----------------------
int main(int argc, char* argv[]) {
 
  int numTransAddr = 0;
  
  addr_File = fopen(argv[1],"r");

  backing_store = fopen("BACKING_STORE.bin","rb");

  if((addr_File == NULL) ||(backing_store == NULL)) 
  {
    printf("Error opening files.\n");
    return -1;
  }

  while(fgets(addr, BUF_SIZE, addr_File) != NULL) 
  {
    logicalAddr = atoi(addr);
    getPage(logicalAddr);

    numTransAddr++;
    
  }
  print(numTransAddr);
  
  
  return 0;
}

//----------------GET PAGE--------------------
void getPage(int addr) {
    int pageNum = ((addr & ADDR_MASK)>>8);
    int offset = (addr & OFFSET_MASK);

    int frameNum = -1;

    int i;
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
    
    insertInTLB(pageNum, frameNum);
    value = physMem[framNum][offset];
    
    printf("Frame number: %d\n",frameNum);  
    printf("Virtual address: %d , Physical address: %d, Value: %d \n", logicalAddr,(frameNum << 8) | offset,value); 
    
    insertInTLB(pageNum,frameNum);

}

//---------------READ FROM STORE-------------------
void readFromStore(int pageNum) {
    if (fseek(backing_store, pageNum * BYTES, SEEK_SET) != 0) {
        fprintf(stderr, "Error seeking in backing store.\n");
        printf("Error in seeking backing store.\n");

    }
    
    if(fread(buffer, sizeof(signed char), BYTES, backing_store) == 0) 
    {
    fprintf(stderr,"Error in reading backing store.\n");
    printf("Error in reading backing store.\n");

  }

  for(int i = 0; i < BYTES; i++) 
  {
    physMem[firstAvailableFrame][i] = buffer[i];

  }

  pageNumTable[firstAvailablePageNum] = pageNum;
  pageFrameTable[firstAvailablePageNum] = firstAvailableFrame;

  firstAvailableFrame++;
  firstAvailablePageNum++;
  return;
          
}


//-------------INSERT IN TLB-------------------
void insertInTLB(int pageNum, int frameNum)
{
    int i;

  for(i = 0; i < numTLBEntries; i++)
  {
    if(TLBPageNum[i] == pageNum)
      break;
  } 

  if( i == numTLBEntries ) 
  {
    
    if(numTLBEntries < TLB_SIZE) 
    { 

      TLBPageNum[numTLBEntries] = pageNum;
      TLBFrameNum[numTLBEntries] = frameNum;
      
    }

    else 
    {
      for(i = 0; i < TLB_SIZE -1; i++) 
      {
	TLBPageNum[i] = TLBPageNum[i+1];
	TLBFrameNum[i] = TLBFrameNum[i+1];
      }
        
      TLBPageNum[numTLBEntries-1] = pageNum;
      TLBFrameNum[numTLBEntries -1] = frameNum;

    }
  }

  else 
  {
    for( i = i; i < numOfTLBEntries - 1; i++) 
    {
      TLBPageNum[i] = TLBPageNum[i+1];
      TLBFrameNum[i] = TLBFrameNum[i+1];
    }
    if(numTLBEntries < TLB_SIZE) {
      TLBPageNum[numTLBEntries] = pageNum;
      TLBFrameNum[numTLBEntries] = frameNum;
      
    }

    else 
    {
      TLBPageNum[numTLBEntries-1] = pageNum;
      TLBFrameNum[numTLBEntries-1] = frameNum;
    }

    if(numTLBEntries < TLB_SIZE)
      numTLBEntries++;
    
  }
  
    
}

//---------------PRINT---------------------------
void print(int transNum)
{
  double pageFaultRate = pageFaults/(double)transNum;
  double tableHitRate = TLBHits/(double)transNum;

  printf("/tPage fault rate = %f\n",pageFaultRate);
  printf("/tTLB hit rate = %f\n",tableHitRate);

}
