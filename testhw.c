/**
 * simple.c
 *
 * A simple kernel module. 
 * 
 * To compile, run makefile by entering "make"
 *
 * Operating System Concepts - 10th Edition
 * Copyright John Wiley & Sons - 2018
 */

#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/hash.h>
#include <linux/gcd.h>
#include <linux/jiffies.h>
#include <asm/param.h>


/* This function is called when the module is loaded. */
int testhw_init(void)
{
       printk(KERN_INFO "Loading Module\n");
       printk(KERN_INFO "%lu\n", GOLDEN_RATIO_PRIME);
       printk(KERN_INFO "%lu\n", jiffies);
       printk(KERN_INFO "%d\n", HZ);
       return 0;
}

/* This function is called when the module is removed. */
void testhw_exit(void)
{
	printk(KERN_INFO "Removing Module\n");
	printk(KERN_INFO "%lu\n", gcd(3300, 24));
        printk(KERN_INFO "%lu\n", jiffies);
}

/* Macros for registering module entry and exit points. */
module_init(testhw_init);
module_exit(testhw_exit);

MODULE_LICENSE("GPL");
MODULE_DESCRIPTION("HW 1");
MODULE_AUTHOR("Brenda Tang and Chantell Chapman");
