#include <stdio.h>
#include <stdlib.h>



int main (int argc, char **argv){
		FILE *fp = fopen("data.txt", "r");
		if(fp == NULL){
			printf("RIP\n");
			return -1;
		}
		char **stringTab = (char **)malloc(250 * sizeof(char *));
		for(int i = 0; i < 250; i++){
			stringTab[i] = (char *) malloc(25 * sizeof(char));
		}
		int len = 0;
		int index = 0;
		int dif = 0;
		while(getline(&stringTab[index], &len, fp) != -1){
			printf("%d : %s", index,stringTab[index]);
			index++;
		}
		
		printf("leaving while\n");
		
		for(int i = 0; i < 250; i ++){
			char *comp = stringTab[i];
			for(int j = i+1; j < 250; j++){
				char *tmp = stringTab[j];
				for(int k = 0; k < 25; k++){
					if(comp[k] != tmp[k]){
						dif++;
						if(dif >= 2){
							break;
						}
					}
				}
				if(dif <= 1){
					printf("MATCH :dif = %d, comp = %d, tmp = %d\n", dif, i,j);
					printf("comp : %s", comp);
					printf("tmp  : %s", tmp);
				}
				dif = 0;
			}
		}
		for(int i = 0; i < 250; i++){
			free(stringTab[i]);
		}
		free(stringTab);
	}
