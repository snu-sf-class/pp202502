use crate::list::{List, List::*};

/// Implement applyfg.
///
/// This function takes a list, two functions, and another list as input.
/// The implementation should follow these steps:
/// 1. If l is Nil, return Nil.
/// 2. If l is Cons(head, tail):
///    - Apply the function f to the head and store the result to s.
///    - Next, apply the function g to both the tail and s, and store the result to r.
///    - Finally, construct a new list with r as the head and the result of recursively calling applyfg(tail, f, g) as the tail.
///
/// Note: The variable names such as s and r are used for clarity in the explanation. You do not have to use the same variable names.
pub fn applyfg<T, S, R>(l: List<T>, f: fn(T) -> S, g: fn(&List<T>, S) -> R) -> List<R> {
    todo!()
}

/// Implement applyfg_puzzle.
///
/// This function takes a list, two functions, and another list as input.
/// The function should behave as follows:
/// 1. If l is Nil, return Nil.
/// 2. If l is Cons(head, tail):
///    - Apply the function f to the head and store the result to s.
///    - Next, apply the function g to l and s, and store the result to t.
///    - If t is Nil, return a list with only s in it. Otherwise, recursively call applyfg_puzzle with t, f, and g as
///    arguments.
///
/// Note: The variable names such as s and r are used for clarity in the explanation. You do not have to use the same variable names.
///       Input f will always be a pure function.
pub fn applyfg_puzzle<T, S>(l: List<T>, f: fn(&T) -> S, g: fn(List<T>, S) -> List<T>) -> List<S> {
    todo!()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_applyfg_basic() {
        let list = List::from_vec(vec![1, 2, 3]);
        let result = applyfg(
            list,
            |x| x * 2,    // Multiply each element by 2
            |_, s| s + 1, // Add 1 to the mapped value
        );

        let expected = List::from_vec(vec![3, 5, 7]);
        assert_eq!(result, expected);
    }

    #[test]
    fn test_applyfg_empty_list() {
        let list: List<i32> = Nil;
        let result = applyfg(list, |x| x * 2, |_, s| s + 1);

        let expected: List<i32> = Nil;
        assert_eq!(result, expected);
    }

    #[test]
    fn test_applyfg_fold() {
        let list = List::from_vec(vec![1, 2, 3]);
        let result = applyfg(
            list,
            |x| x + 1,
            |t, s| s + t.into_iter().fold(0, |x, acc| x + acc),
        );

        let expected = List::from_vec(vec![7, 6, 4]);
        assert_eq!(result, expected);
    }

    #[test]
    fn test_applyfg_puzzle_empty_list() {
        let list: List<i32> = Nil;
        let result = applyfg_puzzle(list, |x| x * 2, |_, _| Nil);
        let expected: List<i32> = Nil;
        assert_eq!(result, expected);
    }

    #[test]
    fn test_applyfg_puzzle_single_element() {
        let list = List::cons(1, Nil);
        let result = applyfg_puzzle(list, |x| x + 1, |_, _| Nil);
        let expected = List::cons(2, Nil);
        assert_eq!(result, expected);
    }

    #[test]
    fn test_applyfg_puzzle_nested_behavior() {
        let list = List::from_vec(vec![3, 5, 7]);
        let result = applyfg_puzzle(
            list,
            |x| x + 2,
            |_, s| {
                if s > 10 {
                    Nil
                } else {
                    Nil
                }
            },
        );
        let expected = List::from_vec(vec![5]);
        assert_eq!(result, expected);
    }
}
