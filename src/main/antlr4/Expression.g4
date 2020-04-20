// gradle antlr4

grammar Expression;

options {
    language = Java;
}

@header {
    package com.adwo.bi.parser;
}

@members {
    public boolean preserveWhitespacesAndComments = true;
}

parse
 : block EOF
 ;

block
 : (expression | functionCall)*
 ;

aggFunction
 : Sum '(' expression ')'       #sumFunction
 | Count '(' expression ')'     #countFunciton
 | Avg '(' expression ')'       #avgFunciton
 | Max '(' expression ')'       #maxFunciton
 | Min '(' expression ')'       #minFunciton
 ;

normalFunction
 : Identifier '(' exprList? ')' #identifierFunctionCall
 ;

functionCall
 : (aggFunction | normalFunction)
 ;

idList
 : Identifier (',' Identifier)*
 ;

exprList
 : expression (',' expression)*
 ;

expression
 : '-' expression                           #unaryMinusExpression
 | '!' expression                           #notExpression
 | expression '^' expression                #powerExpression
 | expression '*' expression                #multiplyExpression
 | expression '/' expression                #divideExpression
 | expression '%' expression                #modulusExpression
 | expression '+' expression                #addExpression
 | expression '-' expression                #subtractExpression
 | expression '>=' expression               #gtEqExpression
 | expression '<=' expression               #ltEqExpression
 | expression '>' expression                #gtExpression
 | expression '<' expression                #ltExpression
 | expression '==' expression               #eqExpression
 | expression '!=' expression               #notEqExpression
 | expression '&&' expression               #andExpression
 | expression '||' expression               #orExpression
 | expression '?' expression ':' expression #ternaryExpression
 | expression In expression                 #inExpression
 | expression Between expression BAnd expression   #betweenExpression
 | Number                                   #numberExpression
 | Bool                                     #boolExpression
 | Null                                     #nullExpression
 | functionCall indexes?                    #functionCallExpression
 | list indexes?                            #listExpression
 | Identifier indexes?                      #identifierExpression
 | String indexes?                          #stringExpression
 | '(' expression ')' indexes?              #expressionExpression
 ;

list
 : '[' exprList? ']'
 ;

indexes
 : ('[' expression ']')+
 ;

Sum      : 'sum';
Avg      : 'avg';
Count    : 'count';
Max      : 'max';
Min      : 'min';

Between  : 'between';
BAnd     : 'and';
In       : 'in';
Null     : 'null';

Or       : '||';
And      : '&&';
Equals   : '==';
NEquals  : '!=';
GTEquals : '>=';
LTEquals : '<=';
Pow      : '^';
Excl     : '!';
GT       : '>';
LT       : '<';
Add      : '+';
Subtract : '-';
Multiply : '*';
Divide   : '/';
Modulus  : '%';
OBrace   : '{';
CBrace   : '}';
OBracket : '[';
CBracket : ']';
OParen   : '(';
CParen   : ')';
SColon   : ';';
Assign   : '=';
Comma    : ',';
QMark    : '?';
Colon    : ':';

Bool
 : 'true'
 | 'false'
 ;

Number
 : Int ('.' Digit*)?
 ;

IdStart
 :  '\u0024'
 |  '\u0041'..'\u005a'
 |  '\u005f'
 |  '\u0061'..'\u007a'
 |  '\u00c0'..'\u00d6'
 |  '\u00d8'..'\u00f6'
 |  '\u00f8'..'\u00ff'
 |  '\u0100'..'\u1fff'
 |  '\u3040'..'\u318f'
 |  '\u3300'..'\u337f'
 |  '\u3400'..'\u3d2d'
 |  '\u4e00'..'\u9fff'
 |  '\uf900'..'\ufaff'
 |  '.'
 ;

IdPart
 :  IdStart
 |  '\u0030'..'\u0039'
 ;

Identifier
 :(('0'..'9') | IdStart) (IdPart)*
 ;

String
 : ["] (~["\r\n] | '\\\\' | '\\"')* ["]
 | ['] (~['\r\n] | '\\\\' | '\\\'')* [']
 ;

Comment
 : ('//' ~[\r\n]* | '/*' .*? '*/') -> skip
 ;

Space
 : [ \t\r\n\u000C] -> skip
 ;

fragment Int
 : [1-9] Digit*
 | '0'
 ;

fragment Digit
 : [0-9]
 ;