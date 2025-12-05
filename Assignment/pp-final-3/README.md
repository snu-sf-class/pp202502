# Principles of Programming Final - Problem 3 (15 points)

## Objective

Implement the functions in `src/ownership.rs` with appropriate Rust code.

## Requirements

Failure to meet any of these requirements will result in **zero points** for the project:
1. **Safe Rust**:
   - Use only safe Rust. Any use of `unsafe` code will result in rejection.
2. **Preservation of Function Headers**:
   - Do not modify the provided function headers, even if doing so allows your code to compile and pass tests.

## Accessing Rust Standard Library Documentation

Below is a list of links to Rust documentation for various types, keywords, and modules.
You may use the search bar on each page for additional information not listed here.
- [`std`](https://doc.rust-lang.org/std/index.html)
- [`for`](https://doc.rust-lang.org/std/keyword.for.html)
- [`std::option::Option`](https://doc.rust-lang.org/std/option/index.html)
- [`std::rc::Rc`](https://doc.rust-lang.org/std/rc/struct.Rc.html)
- [`std::cell::RefCell`](https://doc.rust-lang.org/std/cell/struct.RefCell.html)
- [`std::collections::HashMap`](https://doc.rust-lang.org/std/collections/struct.HashMap.html)
- [`std::iter::fold`](https://doc.rust-lang.org/std/iter/index.html)
- [`std::panic!`](https://doc.rust-lang.org/std/macro.panic.html)

## Grading Considerations

Grading will include additional test cases beyond those provided.

Timeout: 30 sec.

## How to Test Your Code
Go to http://147.46.215.239:21234/ to run the code.

Run the following command to compile and execute the tests in `src/ownership.rs`:
```bash
cargo test
```

If `cargo test` fails, there is an issue with your code.

You can also run `src/main.rs` by the following command.
```bash
cargo run
```

## How to Format and Lint Your Code

To automatically format your code:
```bash
cargo fmt
```

To analyze your code and receive suggestions for fixes:
```bash
cargo clippy
```

## Submission Guidelines

- Only the provided `src/ownership.rs` file will be used for testing. **Any additional files you create will not be included.**
- Submissions will be graded based on the latest commit to the main branch befor the deadline.
  Ensure your changes are committed and pushed to the repository.

```bash
git add src
git commit -m "Add a meaningful commit message"
git push
```

## Errata and Ambiguities

- A protected `errata` branch will contain any corrections or updates. Check this branch periodically for changes.
- If you ecounter ambiguities in the assignment description, report them via the GitHub issue tracker: https://github.com/snu-sf-class/pp202402/issues


