#[derive(Debug, Eq, Hash, PartialEq, Clone, Default)]
pub enum List<T> {
    #[default]
    Nil,
    Cons(T, Box<List<T>>),
}
impl<T> List<T> {
    pub fn iter(&self) -> ListIterator<T> {
        self.into_iter()
    }
    pub fn new() -> List<T> {
        Nil
    }
    pub fn cons(hd: T, tl: List<T>) -> List<T> {
        Cons(hd, Box::new(tl))
    }
    pub fn find<F>(&self, check: F) -> Option<&T>
    where
        F: Fn(&T) -> bool,
    {
        match self {
            Nil => None,
            Cons(h, t) => {
                if check(h) {
                    Some(h)
                } else {
                    t.find(check)
                }
            }
        }
    }

    pub fn from_vec(vec: Vec<T>) -> Self {
        let mut result = List::new();
        for x in vec.into_iter().rev() {
            result = List::cons(x, result);
        }
        result
    }
}
use List::*;

pub struct ListIterator<'a, T> {
    current: &'a List<T>,
}

impl<'a, T> IntoIterator for &'a List<T> {
    type Item = &'a T;
    type IntoIter = ListIterator<'a, T>;

    fn into_iter(self) -> Self::IntoIter {
        ListIterator { current: self }
    }
}

impl<'a, T> Iterator for ListIterator<'a, T> {
    type Item = &'a T;

    fn next(&mut self) -> Option<Self::Item> {
        match self.current {
            Nil => None,
            Cons(head, tail) => {
                self.current = tail;
                Some(head)
            }
        }
    }
}
