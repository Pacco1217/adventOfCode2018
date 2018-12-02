#include <stdio.h>
#include <stdlib.h>



int main (int argc, char **argv){
		FILE *fp = fopen("/home/Pacco/adventOfCode2018/december/2/data", "r");
		if(fp == NULL){
			printf("RIP\n");
			return -1;
		}
		char *string = (char *)malloc(25 * sizeof(char));
		int len = 0;
		int count[26];
		int nbTwo = 0;
		int nbThree = 0;
		int x = 0;
		int isTwo, isThree = 0;
		int c = 0;
		while(getline(&string, &len, fp) != -1){
			printf("%s",string);
			while (string[c] != '\0') {
				if (string[c] >= 'a' && string[c] <= 'z') {
					x = string[c] - 'a';
					count[x]++;
				}
				c++;
			}
			c = 0;
			for(int i = 0; i < 26; i++){
				if(count[i] == 2)
					isTwo = 1;
				if(count[i] == 3)
					isThree = 1;
			}
			nbTwo += isTwo;
			isTwo = 0;
			nbThree += isThree;
			isThree = 0;
			
			for(int i = 0; i < 26; i++){
				count[i] = 0;
			}

		}
		printf("result : %d\n", nbTwo * nbThree);
		free(string);
		
}
