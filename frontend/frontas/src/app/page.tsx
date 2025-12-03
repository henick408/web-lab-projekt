import Header from "./_components/header";
import SideMenu from "./_components/sideMenu";
import Post from "./_components/post";

export default function Home() {
  return (
    <div className=" min-h-screen  bg-zinc-50 cursor-default">
      <Header />
      <div className="flex justify-between w-full">
        <SideMenu />
        <div className="w-full mt-2 rounded flex flex-col items-center gap-5">
          <Post />
          <Post />
          <Post />
          <Post />
          <Post />
          <Post />
          <Post />
          <Post />
          <Post />
          <Post />
        </div>
      </div>
    </div>
  );
}
