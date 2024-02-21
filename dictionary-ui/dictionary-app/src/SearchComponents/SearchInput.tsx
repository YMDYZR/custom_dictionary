//SearchInput.tsx
//キーワード検索の入力値を受け取るコンポーネント
import SearchIcon from '@mui/icons-material/Search';
import './Search.css';

// propsの型エイリアス
type Props = {
    keyWord: string;
    setKeyWord: React.Dispatch<React.SetStateAction<string>>;
};

export const SearchInput = ({ keyWord, setKeyWord }: Props) => {
    return (
        <div className='SearchArea'>
            <SearchIcon />
            {/* TODO:バリデーションを設定 */}
            <input
                type="text"
                name="keyWord"
                maxLength={40}
                placeholder="キーワードを入力"
                value={keyWord}
                onChange={e => setKeyWord(e.target.value)}
            />
        </div>
    );
};


















//export default SearchInput;


// import {useForm} from 'react-hook-form';

// function SearchInput(props: {
//     value: string;
//     onChange: (value: string) => void
// })
// {
//     // 親コンポーネントから受け取ったprops
//     const { value, onChange } = props;

//     // inputタグの値が変更された時に実行されるハンドラ
//     const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
//         const { value } = event.target;
//         // 入力値の変更を親コンポーネントに通知する関数を呼び出す
//         onChange(value);
//     };

//     // 入力値のバリデーションを行う関数
//     const validateInput = (value: string) => {
//         // 入力値が40文字より多い場合
//         if (value.length > 40) {
//             return "キーワードは40文字以内で入力してください。";
//         }
//         // 入力値に問題がない場合
//         return "";
//     };

//     // 入力値に応じてエラーメッセージを取得する
//     const errorMessage = validateInput(value);

//     return (
//         <div>
//             <input type="text" placeholder="キーワードを入力" value={value} onChange={handleChange} />
//             {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
//         </div>
//     );
// }

// export default SearchInput;