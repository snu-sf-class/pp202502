//! DO NOT MAKE CHANGES ON THIS FILE
//! Use functions below to debug your code.

pub const SIZE: usize = 4;
pub const SUBGRID_SIZE: usize = 2;

/// Function to print the Sudoku grid
pub fn print_grid(grid: &[[u8; SIZE]; SIZE]) {
    for row in grid.iter() {
        println!(
            "{}",
            row.iter()
                .map(|&cell| if cell == 0 {
                    '.'
                } else {
                    (cell + b'0') as char
                })
                .collect::<String>()
        );
    }
}

/// Function to parse the Sudoku grid
pub fn parse_grid(grid_str: &str) -> [[u8; SIZE]; SIZE] {
    let mut grid = [[0u8; SIZE]; SIZE];
    let mut row = 0;

    for (i, ch) in grid_str.chars().filter(|&c| c != '\n').enumerate() {
        let col = i % SIZE;
        if col == 0 && i > 0 {
            row += 1;
        }

        grid[row][col] = if ch == '.' {
            0
        } else {
            ch.to_digit(10).unwrap() as u8
        };
    }

    grid
}
