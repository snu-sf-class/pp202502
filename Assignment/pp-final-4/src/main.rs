mod grid;
mod sudoku;

fn main() {
    let grid_str = "\
1.34
3.12
2.4.
4.2.";

    let mut grid = grid::parse_grid(grid_str);

    println!("Input Grid:");
    grid::print_grid(&grid);

    sudoku::solve(&mut grid);

    println!("Solved Grid:");
    grid::print_grid(&grid);
}
