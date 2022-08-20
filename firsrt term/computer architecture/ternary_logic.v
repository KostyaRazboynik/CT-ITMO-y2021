module not_switch(in, out); //not
  input in;
  output out;
  
  supply1 power;
  supply0 ground;
  
  pmos p1(out, power, in);
  nmos n1(out, ground, in);
  
endmodule

module nand_switch(a, b,out); //nand
  input a, b;
  output out;
  wire w;
  
  supply1 power;
  supply0 ground;
	
  nmos n1(w, ground, b);
  nmos n2(out, w, a);
  pmos p1(out, power, a);
  pmos p2(out, power, b);
endmodule

module and_switch(a, b, out); //and
  input a, b;
  output out;
  
  wire w;
  
  nand_switch my_nand(a, b, w);
  not_switch my_not(w, out);
  
endmodule

module nor_switch(a, b,out); //nor
  input a, b;
  output out;
  wire w;
  
  supply1 power;
  supply0 ground;
	
  nmos n1(out, ground, a);
  nmos n2(out, ground, b);
  
  pmos p1(w, power, a);
  pmos p2(out, w, b);
endmodule

module or_switch(a, b, out); //or
  input a, b;
  output out;
  wire w;
  
  nor_switch my_nor(a, b, w);
  not_switch my_not (w, out);
endmodule


module ternary_min(a0, a1, b0, b1, out0, out1);
  input a0, a1, b0, b1;
  output out0, out1;
  wire x, y, z, w;
  
  or_switch my_or1(x, y, w);
  or_switch my_or2(z, w, out0);
  
  and_switch my_and3(a0, b0, z);
  and_switch my_and1(a0, b1, x);
  and_switch my_and2(a1, b0, y);  
  and_switch my_and0(a1, b1, out1);
  
  
endmodule

module ternary_any(a0, a1, b0, b1, out0, out1); //any
  input a0, a1, b0, b1;
  output out0, out1;
  wire x, y, z, w, q, nota0, nota1, notb0, notb1, e, r, t, o, u, f, l, p, c, d, n;
  
  not_switch my_not_a0(a0, nota0);
  not_switch my_not_a1(a1, nota1);
  not_switch my_not_b0(b0, notb0);
  not_switch my_not_b1(b1, notb1);
  
  and_switch my_and1(nota0, nota1, x);
  and_switch my_and2(x, notb0, y);
  and_switch my_and3(y, b1, z);
  
  and_switch my_and4(nota0, a1, e);
  and_switch my_and5(e, notb0, r);
  and_switch my_and6(r, notb1, t);
  
  and_switch my_and7(a0, nota1, o);
  and_switch my_and8(o, b0, u);
  and_switch my_and9(u, notb1, q);
  
  or_switch my_or1(z, t, w);
  or_switch my_or2(w, q, out0); //out0
  
  and_switch my_and10(r, b1, f);
  
  and_switch my_and11(e, b0, l);
  and_switch my_and12(l, notb1, p);
  
  and_switch my_and13(o, notb0, c);
  and_switch my_and14(c, b1, d);
  
  or_switch my_or3(f, p, n);
  or_switch my_or4(n, d, out1); //out1
endmodule