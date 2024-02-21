//SearchForm.tsx
import { useState, useEffect } from 'react';
import { SearchInput } from "./SearchComponents/SearchInput";
import SearchTag from './SearchComponents/SearchTags';
import axios from 'axios';
import { error } from 'console';
import SearchResults from './SearchComponents/SeaarchResults';

//デバウンス関数(入力が止まってから1秒経過した時に自動で通信を行う)
function debounce(func: (...args: any[]) => void, delay: number) {
  let debounceTimer: NodeJS.Timeout;
  return function (...args: any[]) {
    clearTimeout(debounceTimer);
    debounceTimer = setTimeout(() => func(...args), delay);
  }
}

//選択されたタグを配列として受け取る。
const Search = () => {
  //検索キーワードを保存する
  const [keyWord, setKeyWord] = useState<string>("");
  //タグを保存する
  const [tag, setTag] = useState<boolean>(false);
  //デバウンスの計算
  const [debouncedKeyWord, setDebouncedKeyWord] = useState(keyWord);
  //検索結果
  const [response, setResponse] = useState<{ termName: string, summary: string, description: string, tagName: string[] }[] | null>(null);

  //keyWordが更新される度にカウントを0に戻す。
  useEffect(() => {
    const timerId = setTimeout(() => {
      setDebouncedKeyWord(keyWord);
    }, 1000);//一秒後にdebouncedKeyWordを更新

    //クリーンアップ関数
    return () => {
      clearTimeout(timerId);
    };
  }, [keyWord]);


  //debouncedKeyWordが更新されたときにデータを送信し、検索結果を取得
  useEffect(() => {
    const data = { keyWord: debouncedKeyWord, tag: tag };

    axios.post('http://localhost:8080/search/keywords', data, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(res => {
        //responseをstateにセットする。
        setResponse(res.data);
      })
      .catch(error => console.log(error));
  }, [debouncedKeyWord, tag]);

  // この部分が実行できていない。
  const handleChange = debounce((event: React.ChangeEvent<HTMLInputElement>) => {
    setKeyWord(event.target.value);
  }, 1000);

  return (
    <div>
      {/* ajax通信を行うため<button>は設置しない。 */}
      <form>
        <div>タグ検索を行う<SearchTag tag={tag} setTag={setTag} />
        </div>
        <SearchInput keyWord={keyWord} setKeyWord={setKeyWord} /><br></br>
      </form>
      {/* 検索結果を表示するコンポーネント */}
      <SearchResults response={response} setResponse={setResponse} />
    </div>
  );
};
export default Search;

