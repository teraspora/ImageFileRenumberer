# Image File Renumberer

## Rationale

When generating fractal images of Julia sets with an escape-time algorithm, I generate big sequences of image files in `.png` format, as I vary the constant which defines the Julia set for a particular function, so that I can combine them to make videos of the mutating fractal.

I name these `img00000.png`, `img00001.png` etc.

I often then make a title sequence and an ending sequence.   These I also number from zero.   So in order to concatenate them it's necessary to add the length of the title sequence to the numbers in the main sequence, and add the length of these two combined to the numbers in the end sequence, so that I end up with a composite sequence of `n` images, numbered sequentially from zero to `n-1`.

This program, then, takes a sequence of numbered `.png` files as input, and renumbers them by adding or subtracting a constant to/from each number.

## Usage

Usage:  `java ImageFileRenumberer <path to image files> -n | +n`
e.g.  `java ImageFileRenumberer /home/john/photos/ +12`
will rename files by adding 12 to each number.

All image indices need to be positive, so if `k` is the minimum index (usually zero), and the increment `n` is negative, the program will complain if `abs(n) < k`.

It will also complain if an invalid path is supplied, or if the characters in the index position cannot be parsed to a positive integer, but if a valid path is supplied it will simply ignore files whose names do not match the required pattern.

It is assumed that the prefixes will all be the same, e.g. `img`.   The initial character of the prefix will be changed in order to avoid overwriting any files.   If some begin with `i` and some with `j` files may indeed be overwritten.   I intend to fix this at some point but normally all prefixes will be the same so it shouldn't be an issue.   As files are renamed in place, users are encouraged to operate on a copy of the target folder in order to prevent any possibility of data loss. 
