external annotation class <!WRONG_EXTERNAL_DECLARATION!>A<!>

val x: Int
    <!WRONG_EXTERNAL_DECLARATION!>external get()<!> = noImpl

class B

val B.x: Int
    <!WRONG_EXTERNAL_DECLARATION!>external get()<!> = noImpl