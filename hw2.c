/*
Chantell Chapman and Brenda Tang
Hw 2
*/

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

//Structure for passing parameters to thread
typedef struct
{   
    int row; // The starting row
    int col; // The starting column
    int (* board)[9]; // The pointer to the board
} parameters;

void * walk_rows(void * params); // Prototype for the walk_rows function
void * walk_cols(void * params); // Prototype for the walk_cols function
void * check_square(void * params); // Prototype for 3x3 square function


int main(void)
{
    //---- Create Sudoku board--------
    int board[9][9] = {
            {6, 2, 4, 5, 3, 9, 1, 8, 7},
            {5, 1, 9, 7, 2, 8, 6, 3, 4},
            {8, 3, 7, 6, 1, 4, 2, 9, 5},
            {1, 4, 3, 8, 6, 5, 7, 2, 9},
            {9, 5, 8, 2, 4, 7, 3, 6, 1},
            {7, 6, 2, 3, 9, 1, 4, 5, 8},
            {3, 7, 1, 9, 5, 6, 8, 4, 2},
            {4, 9, 6, 1, 8, 2, 5, 7, 3},
            {2, 8, 5, 4, 7, 3, 9, 1, 6}
        };
  
    //Create parameter for the columns and rows check
    parameters * param0 = (parameters *) malloc(sizeof(parameters));
    param0->row = 0;
    param0->col = 0;
    param0->board = board;
  
    //Create parameters for the nine 3x3 threads
    //1st 3x3
    parameters * param1 = (parameters *) malloc(sizeof(parameters));
    param1->row = 0;
    param1->col = 0;
    param1->board = board;
  
    //2nd 3x3
    parameters * param2 = (parameters *) malloc(sizeof(parameters));
    param2->row = 0;
    param2->col = 3;
    param2->board = board;
  
    //3rd 3x3
    parameters * param3 = (parameters *) malloc(sizeof(parameters));
    param3->row = 0;
    param3->col = 6;
    param3->board = board;
  
    //4th 3x3
    parameters * param4 = (parameters *) malloc(sizeof(parameters));
    param4->row = 3;
    param4->col = 0;
    param4->board = board;
  
    //5th 3x3
    parameters * param5 = (parameters *) malloc(sizeof(parameters));
    param5->row = 3;
    param5->col = 3;
    param5->board = board;
  
    //6th 3x3
    parameters * param6 = (parameters *) malloc(sizeof(parameters));
    param6->row = 3;
    param6->col = 6;
    param6->board = board;
  
    //7th 3x3
    parameters * param7 = (parameters *) malloc(sizeof(parameters));
    param7->row = 6;
    param7->col = 0;
    param7->board = board;
  
    //8th 3x3
    parameters * param8 = (parameters *) malloc(sizeof(parameters));
    param8->row = 6;
    param8->col = 3;
    param8->board = board;
  
    //9th 3x3
    parameters * param9 = (parameters *) malloc(sizeof(parameters));
    param9->row = 6;
    param9->col = 6;
    param9->board = board;
  
    //Create the threads
    pthread_t thread_rows, thread_cols, thread1, thread2, thread3, thread4, thread5, thread6, thread7, thread8, thread9;
  
    //Create return values for the threads
    void * all_rows;
    void * all_cols;
    void * square1;
    void * square2;
    void * square3;
    void * square4;
    void * square5;
    void * square6;
    void * square7;
    void * square8;
    void * square9;
  
    //Initialize the nine threads
    pthread_create(&thread_rows, NULL, walk_rows, (void *) param0);
    pthread_create(&thread_cols, NULL, walk_cols, (void *) param0);
    pthread_create(&thread1, NULL, check_square, (void *) param1);
    pthread_create(&thread2, NULL, check_square, (void *) param2);
    pthread_create(&thread3, NULL, check_square, (void *) param3);
    pthread_create(&thread4, NULL, check_square, (void *) param4);
    pthread_create(&thread5, NULL, check_square, (void *) param5);
    pthread_create(&thread6, NULL, check_square, (void *) param6);
    pthread_create(&thread7, NULL, check_square, (void *) param7);
    pthread_create(&thread8, NULL, check_square, (void *) param8);
    pthread_create(&thread9, NULL, check_square, (void *) param9);

    //Wait for threads to finish their tasks
    pthread_join(thread_rows, &all_rows);
    pthread_join(thread_cols, &all_cols);
    pthread_join(thread1, &square1);
    pthread_join(thread2, &square2);
    pthread_join(thread3, &square3);
    pthread_join(thread4, &square4);
    pthread_join(thread5, &square5);
    pthread_join(thread6, &square6);
    pthread_join(thread7, &square7);
    pthread_join(thread8, &square8);
    pthread_join(thread9, &square9);
  
    //Check whether the Sudoku Puzzle was valid
    if (    (long) all_rows == 1 && //got error using int, changed to long
            (long) all_cols == 1 &&
            (long) square1 == 1 &&
            (long) square2 == 1 &&
            (long) square3 == 1 &&
            (long) square4 == 1 &&
            (long) square5 == 1 &&
            (long) square6 == 1 &&
            (long) square7 == 1 &&
            (long) square8 == 1 &&
            (long) square9 == 1 ) 
    {
        printf("The Sudoku Puzzle is valid!\n");
    }

    else 
    {
        printf("The Sudoku Puzzle is NOT valid.\n");
    }
  
    return 0;
}


//Checks each row if it contains all digits 1-9
void * walk_rows(void * params) 
{
    parameters * data = (parameters *) params;
    int startRow = data->row;
    int startCol = data->col;
    for (int i = startRow; i < 9; ++i) 
    {
        int row[10] = {0};
        for (int j = startCol; j < 9; ++j)
         {
            int val = data->board[i][j];
            if (row[val] != 0) 
            {
                return (void *) 0; //return 0 is doesn't contain all digits
            }
            else
            {
                row[val] = 1;
            }
        }
    }
    return (void *) 1; //return 1 if contains all digits
}


//Checks each column if it contains all digits 1-9
void * walk_cols(void * params) 
{
    parameters * data = (parameters *) params;
    int startRow = data->row;
    int startCol = data->col;
    for (int i = startCol; i < 9; ++i) 
    {
        int col[10] = {0};
        for (int j = startRow; j < 9; ++j) 
        {
            int val = data->board[j][i];
            if (col[val] != 0) 
            {
                return (void *) 0; //return 0 is doesn't contain all digits
            }
            else
            {
                col[val] = 1; 
            }
        }
    }
    return (void *) 1; //return 1 if contains all digits
}


//Checks if a 3x3 square contains all numbers 1-9
void * check_square(void * params)
 {
    parameters * data = (parameters *) params;
    int startRow = data->row;
    int startCol = data->col;
    int saved[10] = {0};
    for (int i = startRow; i < startRow + 3; ++i) 
    {
        for (int j = startCol; j < startCol + 3; ++j)
         {
            int val = data->board[i][j];
            if (saved[val] != 0)
             {
                return (void *) 0; //return 0 is doesn't contain all digits
            }
            else
            {
                saved[val] = 1;
            }
        }
    }
    return (void *) 1; //return 1 if contains all digits
}
