fun testing() {
    <caret>SomeClass<List<String>>()
}

//INFO: <b>public</b> <b>constructor</b> SomeClass&lt;T : (<(raw) <a href="psi_element://kotlin.Any">Any</a>?>?&gt;..<(raw) <a href="psi_element://kotlin.Any">Any</a>?>&lt;*&gt;?)&gt;()<br/>Java declaration:<br/>[light_idea_test_case] public class SomeClass&lt;T extends java.util.List&gt; extends Object
