prime(2).
prime(3).
prime(X) :- 1 is X mod 2, \+ notPrime(X, 3).

notPrime(X, Y) :- 0 is X mod Y.
notPrime(X, Y) :- (Y * Y) < X, Y2 is Y + 2, notPrime(X, Y2).

composite(X) :- \+ prime(X).


%nextPrime(1, 2).
nextPrime(2, 3).
nextPrime(N, R) :- 0 is N mod 2, N2 is N + 1, prime(N2), R is N2.
nextPrime(N, R) :- 0 is N mod 2, N2 is N + 1, \+ prime(N2), nextPrime(N2, R).
nextPrime(N, R) :- \+ 0 is N mod 2, N2 is N + 2, prime(N2), R is N2.
nextPrime(N, R) :- \+ 0 is N mod 2, N2 is N + 2, \+ prime(N2), nextPrime(N2, R).

union([], R, R).
union([H | T], R, [H | I]) :- union(T, R, I).

divisors(1, _, []).
divisors(N, D, R) :- 0 is N mod D, M is div(N, D), prime(D), divisors(M, D, R2), union([D | _], R2, R).
divisors(N, D, R) :- nextPrime(D, DN), divisors(N, DN, R).

prime_divisors(N, R) :- divisors(N, 2, R), !.


nth_prime(N, P) :-
    nthDivisors(N, 1, 2, R),
    P = R.

nthDivisors(N, C, P, R) :-
    C == N -> R = P;
    nextPrime(P, P1),
    C1 is C + 1, !,
    nthDivisors(N, C1, P1, R).


/*
uDivisors([], []).
uDivisors([H], [H]).
uDivisors([H, H2 | T], R) :-
    H == H2 -> uDivisors([H2 | T], R2), R = R2;
    uDivisors([H2 | T], R2), R = [H | R2].

unique_prime_divisors(N, U) :-
    prime_divisors(N, R),
    uDivisors(R, R2),
    U = R2.
*/